
define(['jquery','ChineseDistricts','distpicker','handlebar','common'], 
		function(jquery,distpicker_data,distpicker,Handlebars,common){
	var aId =null;//地址Id，通过他判断是修改还是添加保存
	Handlebars.registerHelper("state",function(default_addr,id){
		//判断是否是默认地址绑定对应的按钮
		if(default_addr==1){
			return new Handlebars.SafeString("<div addr_id="+id+" class='t_default'>默认地址</div>");
		}
		return new Handlebars.SafeString('<button class="btn_default" addr_id='+id+' style="background:#fff;border:none;">设为默认</button>');
	});
	
	function ready(){
		//设置三级联动不选中
		$("#distpicker1").distpicker("destroy");
		$("#distpicker1").distpicker({
			//动态赋值
			autoSelect:false
		});
		//显示所有地址信息
		showAddressInfo();
		//设置默认地址事件绑定
		$("#address_container").on("click",'.btn_default',function(){
			//获得地址id
			var addrId = $(this).attr("addr_id");
			//后台请求
			$.ajax({
				url:baseUrl+"addr/setdefault.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:{"id":addrId},
				dataType:'json',
				type:'get',
				success:function(rs){
					updatePageInfo(rs);
				}
			});
		});
		
		//修改事件绑定
		$("#address_container").on("click",".addr_update",function(){
			//获得地址id
			var addrId = $(this).attr("addr_id");
			aId=addrId;
			//后台请求
			$.ajax({
				url:baseUrl+"addr/findAddressById.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				async:false, //同步
				data:{"id":addrId},
				dataType:'json',
				type:'post',
				success:function(rs){
					//数据返回成功，信息添加到页面中
					fillAddress(rs.data);
					$("#hint").html("修改地址");
				}
			});
		});
		
		//删除事件绑定
		//1.最外层，删除按钮事件
		$("#address_container").on("click",".addr_del",function(){
			//提示删除信息
			// var addrId = $(this).attr("addr_id");
			var result = confirm("是否删除该地址？");
			if(!result){
				return;
			}
			//获得地址id
			var addrId = $(this).attr("addr_id");
			
			//2.第二层，获取默认地址
			var oldaddr = -1;
			getDefaultAjax = $.ajax({
				url:baseUrl+"addr/getdefault.do",
				dataType:'json',
				async:false, //同步
				success:function(rs){
					if(rs.status == 0){
						oldaddr=rs.data.id;
//alert("1.默认地址ID="+oldaddr);
					}else{
						alert(rs.msg);
					}
				}
			});
			
			//3.第三层，删除地址
			$.when(getDefaultAjax).done(function () {
				
				delAjax = $.ajax({
					url:baseUrl+"addr/deladdr.do",
					xhrFields:{withCredentials:true},
					crossDomain:true,
					async:false, //同步
					data:{"id":addrId},
					dataType:'json',
					type:'get',
					success:function(rs){
						//数据返回成功，信息添加到页面中
alert("2.删除地址成功！");
						updatePageInfo(rs);
					}
				});
				
				//4.第四层，获取所有地址并设置第一条为默认地址
				$.when(delAjax).done(function () {
					if(oldaddr<0) return;
					if(oldaddr == addrId){
						var firstAddr = -1;
						getAllAjax = $.ajax({
							url:baseUrl+"addr/findaddrs.do",
							xhrFields:{withCredentials:true},
							crossDomain:true,
							async:false, //同步
							dataType:'json',
							success:function(rs){
//alert("3.刚才删除了默认地址，开始重置！");
								//数据成功返回，将后台信息添加到前端
								//updatePageInfo(rs);
								if(rs.data.length > 0){
									firstAddr = rs.data[0].id;
								}
							}
						});
						$.when(getAllAjax).done(function () {
							if(firstAddr<0) return;
							$.ajax({
								url:baseUrl+"addr/setdefault.do",
								xhrFields:{withCredentials:true},
								crossDomain:true,
								data:{"id":firstAddr},
								dataType:'json',
								type:'get',
								success:function(rs){
//alert("4.默认地址重置成功！");
									updatePageInfo(rs);
								}
							});
						});
					}
				});
			});
			
		});
		//保存
		$("#btnSave").click(function(){
			//验证
			if(!validate()){
				return;
			}
			var hint = $('#hint').text();
		//alert(substr(hint, 0, 49));//弹出前50个字符
			if(hint == "修改地址"){
				$("#hint").html("新增收货地址");
			}
			//提取数据提交后台
			var formData={
					"name":$("#consignee").val(),
					"mobile":$("#phone").val(),
					"province":$("#eprovinceName").find("option:selected").text(),
					"city":$("#ecityName").find("option:selected").text(),
					"district":$("#edistrictName").find("option:selected").text(),
					"zip":$("#zip").val(),
					"addr":$("#detailAddr").val()
			};
			//通过aID判断添加还是修改
			if(aId!=null){
				formData["id"]=aId;
				aId=null;
			}
//			console.log(formData);
//			直接更新
			saveAddrAjax=$.ajax({
				url:baseUrl+"addr/saveaddr.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:formData,
				dataType:'json',
				type:'post',
				success:function(rs){
					setFirstDefaultAddr();
					//数据返回成功 更新信息
					updatePageInfo(rs);
					//清空地址信息
					clearAddress();
				}
			});
//			然后判断是不是有默认地址
			
			$.when(saveAddrAjax).done(function(){
				var defaultAddrNum=0;
				//当完成保存时判断是不是有默认地址，如果没有就获取新的地址id然后设置为默认
				getDefaultaddressAjax=$.ajax({
					url:baseUrl+"addr/getdefault.do",
					dataType:'json',
					async:false, //同步
					success:function(rs){
						if(rs.status == 0){//已经有了默认地址
//							没有默认地址的话，将第一个设为默认
//							先获取地址
							defaultAddrNum=1;
						}
					}
				});
//				判断是不是有了默认地址
				if(!defaultAddrNum){  //如果没有默认
//					先获取地址列表
					console.log("无默认地址");
					var futureDefaultAddrId;
					getAddrAjax=$.ajax({
						url:baseUrl+"addr/findaddrs.do",
						xhrFields:{withCredentials:true},
						crossDomain:true,
						dataType:'json',
						success:function(rs){
							futureDefaultAddrId=rs.data[0].id;
						}
					});
					$.when(getAddrAjax).done(function(){
						$.ajax({
							url:baseUrl+"addr/setdefault.do",
							xhrFields:{withCredentials:true},
							crossDomain:true,
							data:{"id":futureDefaultAddrId},
							dataType:'json',
							type:'get',
							success:function(rs){
								updatePageInfo(rs);
							}
						});
					});
				}
				
				
				
			});
			
			
			
		});
		
		//单击搜索
	    $("#searchBtn").click(function(){
	    	var proName = $("#keyword").val();
	    	$(window).attr("location","product_search.html?name="+proName);
	    });
	}
	
	//显示所有地址信息
	function showAddressInfo(){
		$.ajax({
			url:baseUrl+"addr/findaddrs.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			success:function(rs){
				//数据成功返回，将后台信息添加到前端
				updatePageInfo(rs);
			}
		});
	}
	
	//后台信息添加到前端
	function updatePageInfo(rs){
		//判断方法是否成功
		if(rs.status==0){
			//预编译模板
			var tpl=$("#address_tpl").html();
			var func =Handlebars.compile(tpl);
			var result = func(rs.data);
			$("#address_container").html(result);
		}else{
			//未登录跳转登录页面
			$(window).attr("location","login.html");
		}
	}
	
	//获得地址信息填充标签显示
	function fillAddress(data){
		//填充省份市区
		$("#distpicker1").distpicker("destroy");//销毁实例
		$("#distpicker1").distpicker({
			province:data.province,
			city:data.city,
			district:data.district
		});
		//填充其他
		$("#detailAddr").val(data.addr);
		$("#zip").val(data.zip);
		$("#consignee").val(data.name);
		$("#phone").val(data.mobile);
	}
	function setFirstDefaultAddr(){
		
	}
	//验证
	function validate(){
		var province =$("#eprovinceName").find("option:selected").attr("data-code");
//		console.log(province);
		if(province==null || province==""){
			alert("请选择省份！");
			return false;
		}
		var city =$("#ecityName").find("option:selected").attr("data-code");
		if(city==null || city==""){
			alert("请选择城市！");
			return false;
		}
		var district =$("#edistrictName").find("option:selected").attr("data-code");
		if(district==null || district==""){
			alert("请选择区！");
			return false;
		}
		var detail=$("#detailAddr").val();
		if(detail==null || detail==""){
			alert("请输入详细地址！");
			return false;
		}
		var zip = $("#zip").val();
		if(zip!=null && zip!=""){
			var reg =/^[0-9]{6}$/;
			if(!reg.test(zip)){
				alert("请输入正确格式的邮编！");
				return false;
			}
		}
		var name =$("#consignee").val();
		if(name==null || name==""){
			alert("请输入收货人姓名！");
			return false;
		}
		var phone =$("#phone").val();
		if(phone==null || phone==""){
			alert("请输入收货人电话！");
			return false;
		}
		var reg = /^1[0-9]{10}$/;
		if(!reg.test(phone)){
			alert("请输入正确格式的手机号！");
			return false;
		}
		return true;
	}
	
	//清空地址信息
	function clearAddress(){
		//恢复省市区
		$("#distpicker1").distpicker("destroy");
		$("#distpicker1").distpicker({
			//动态赋值
			autoSelect:false
		});
		//填充其他
		$("#consignee").val('');
		$("#phone").val('');
		$("#zip").val('');
		$("#detailAddr").val('');
	}
	return{
		ready:ready
	};
});





