define(['jquery','handlebar','common'],function(jquery,Handlebars,common){
	function ready(){
		//1.读取登录用户的购物车信息
		getCartInfo();
		
		//2.为购物车中每个商品绑定删除事件
		$(".delete").live("click",function(){
			var result = confirm("是否删除该商品？");
			if(!result){
				return;
			}
			var productId = $(this).attr("data-product-id");
			$.ajax({
				url:baseUrl+"cart/delcarts.do",
				data:{"productId":productId},
				dataType:'json',
				xhrFields:{withCredentials:true},
				crossDomain:true,
				success:function(rs){
					//数据返回成功更新页面
					updatePageInfo(rs);
				}
			});
		});
		
		//3.清空购物车
		$("#clear").click(function(){
			var result = confirm("是否清空购物车？");
			if(!result){
				return;
			}
			$.ajax({
				url:baseUrl+"cart/clearcarts.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				dataType:'json',
				success:function(rs){
					//判断方法是否成功
					if(rs.status==0){
						$("#cart-empty").css("display","block");
						$("#cart-container").css("display","none");
					}else{
						alert(rs.msg);
					}
				}
			});
		});
		
		//4.更新购物车数量 ，减数量
		$(".minus_btn").live("click",function(){
			//判断商品数量
			var quantity= $(this).next().val();
			quantity = parseInt(quantity)-1;
			if(quantity<=0){
				$(this).next().val(1);
				alert("商品数量不能小于1！");
				return;
			}
			//更新数据
			var productId= $(this).attr("data-product-id");
			//更新购物车信息
			updateCartInfo(productId,quantity);
			getCartInfo();
			
		});
		
		//5.更新购物车数量 ，增数量
		$(".plus_btn").live("click",function(){
			//判断商品数量
			var quantity = $(this).prev().val();
			var stock = $(".plus_btn").prev().attr("data-stock");
			quantity = parseInt(quantity)+1;
			if(quantity>stock){
				$(this).prev().val(stock);
				alert("商品数量不能大于库存量！");
				return;
			}
			//更新数据
			var productId= $(this).attr("data-product-id");
			//更新购物车信息
			updateCartInfo(productId,quantity);
			getCartInfo();
			
		});
//加功能：订单生成并添加至数据库
//-》不用，在订单确认页 跳转至 订单详情页时，会添加至数据库
		//6.确认订单
		$("#submit").click(function(){
			var result = confirm("是否确认下单？");
			if(!result){
				return;
			}
			if($("#amount").text()=="￥0"){
				alert("订单商品不能为空");
				return;
			}
			$(window).attr("location","order_confirm.html");
		});
		
		//全选
		$("#selectAll").click(function(){
			//判断全选是否选中
			if($(this).prop("checked")){
				$(".all_checkbox").prop("checked",true);
				//更新购物车信息
				$(".all_checkbox").each(function(){
					var productId =$(this).attr("data-product-id");
					quantity = $("#quantity"+productId).val();
					updateCartInfo(productId,quantity,1);
				});
			}else{
				$(".all_checkbox").prop("checked",false);
				//更新购物车信息
				$(".all_checkbox").each(function(){
					var productId =$(this).attr("data-product-id");
					quantity = $("#quantity"+productId).val();
					updateCartInfo(productId,quantity,0);
				});
			}
			//获取购物车信息
			getCartInfo();
			
		});
		
		//反选
		$(".all_checkbox").live("click",function(){
			checkList();
			var checked =0;
			if($(this).is(":checked")){
				checked=1;
			}else{
				checked=0;
			}
			//更新数据
			var productId = $(this).attr("data-product-id");
			quantity = $("#quantity"+productId).val();
			
			updateCartInfo(productId,quantity,checked);
			getCartInfo();
		
		});
		
		
		
		
		//单击搜索
	    $("#searchbtn").click(function(){
	    	var proName = $("#keyword").val();
	    	$(window).attr("location","product_search.html?name="+proName);
	    });
	    
	    
		
	}
	
	//更新购物车信息
	function updateCartInfo(productId,count,checked){
		$.ajax({
			url:baseUrl+"cart/updatecarts.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'productId':productId,'count':count,'checked':checked},
			dataType:'json',
			async:false,
			success:function(rs){
				//数据返回成功
//alert("更新购物车 数据库成功");
				updatePageInfo(rs);
			}
		});
	}
	//读取购物车信息
	function getCartInfo(){
		$.ajax({
			url:baseUrl+"cart/findallcarts.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			async:false,//同步
			dataType:'json',
			success:function(rs){
				//数据返回成功
				updatePageInfo(rs);
			}
		});
	}
	
	//根据返回数据更新页面信息
	function updatePageInfo(rs){
		//判断用户是否登录
		if(rs.status==0){
			
			//判断购物车是否存在商品
			if(rs.data.lists.length == 0){
				$("#cart-empty").css("display","block");
				$("#cart-container").css("display","none");
			}else{
				//截取商品名称前6个字符
				for(var i=0;i<rs.data.lists.length;i++){
					var name = rs.data.lists[i].name;
					if(name.length>6)
						rs.data.lists[i].name = name.slice(0,7)+"...";
				}
				$("#cart-empty").css("display","none");
				$("#cart-container").css("display","block");
				//更新购物车列表
				$("#cart-item-container").html();
				var tpl =$("#cart-item-tpl").html();
				var func = Handlebars.compile(tpl);
				var result = func(rs.data.lists);
				$("#cart-item-container").html(result);
				//更新购物车总价格
				$("#amount").html("￥"+rs.data.totalPrice);
				//更新复选框状态
				for(var i=0;i<rs.data.lists.length;i++){
					if(rs.data.lists[i].checked == 1){
						$(".pro00>input").get(i).checked =true;
					}
				}
				//判断是否反选
				checkList();
			}
		}else{
			//未登录直接跳转登录页面
			alert("您还未登录，请登录！");
			$(window).attr("location","login.html");
		}
	}
	
	//反选方法
	function checkList(){
		var one = $(".all_checkbox");
		//记录有多少个子复选框被选中
		var number =0;
//alert("one :"+one.length);
		for(var i=0;i<one.length;i++){
			if(one[i].checked){
				number++;
			}
		}
//alert("number :"+number);
		//子复选框选中个数与总数是否相等
		if(one.length == number){
			$("#selectAll").attr("checked","checked");
		}else{
			$("#selectAll").removeAttr("checked");
		}
	}

	return{
		ready:ready,
	};
})