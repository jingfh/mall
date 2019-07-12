define(['jquery','handlebar','common'],function(jquery,Handlebars,common){
	
	var status= 0;//订单状态
	var pageNum =1;//当前页
	var pageSize = 5;//一页的数量
	
	function ready(){
		//1.读取登录用户的关注信息
		
		
		Handlebars.registerHelper("state",function(status,pid){
			//判断是否是默认地址绑定对应的按钮
			if(status == 2){
				return new Handlebars.SafeString("<a href='product_detail.html?pid="+pid+"' target='_blank'>查看详情</a>");
				
			}
			//return new Handlebars.SafeString('<button class="btn_default" addr_id='+id+' style="background:#fff;border:none;">设为默认</button>');
		});
		
		getLikeInfo();
		
		//2.为购物车中每个商品绑定删除事件
		$(".delete").live("click",function(){
			var result = confirm("是否取消关注该商品？");
			if(!result){
				return;
			}
			var productId = $(this).attr("data-product-id");
//alert(productId);
			$.ajax({
				url:baseUrl+"product/canclelikeproducts.do",
				data:{"productId":productId,"pageNum":pageNum,"pageSize":pageSize},
				dataType:'json',
				xhrFields:{withCredentials:true},
				crossDomain:true,
				success:function(rs){
					//alert(productId);
					//数据返回成功更新页面
					updatePageInfo(rs);
				}
			});
		});
		
		//3.清空关注
		$("#clear").click(function(){
			var result = confirm("是否清空关注列表？");
			if(!result){
				return;
			}
			$.ajax({
				url:baseUrl+"product/clearlikes.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				dataType:'json',
				success:function(rs){
					//判断方法是否成功
					if(rs.status==0){
						$("#like-empty").css("display","block");
						$("#like-container").css("display","none");
					}else{
						alert(rs.msg);
					}
				}
			});
		});
		
		//单击搜索
	    $("#searchbtn").click(function(){
	    	var proName = $("#keyword").val();
	    	$(window).attr("location","product_search.html?name="+proName);
	    });
	    
	    
		
	}
	
	
	//读取关注信息
	function getLikeInfo(){
		$.ajax({
			url:baseUrl+"product/findlikeproducts.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			//async:false,//同步
			dataType:'json',
			data:{"pageNum":pageNum,"pageSize":pageSize},
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
//alert("刷新");
			//截取收货人名字前7个字符
			for(var i=0;i<rs.data.data.length;i++){
				var name = rs.data.data[i].name;
				if(name.length>6)
					rs.data.data[i].name = name.slice(0,6)+"...";
			}
			//判断购物车是否存在商品
			if(rs.data.data.length == 0){
				$("#like-empty").css("display","block");
				$("#like-container").css("display","none");
			}else{
				$("#like-empty").css("display","none");
				$("#like-container").css("display","block");
				//更新购物车列表
				$("#like-item-container").html();
				var tpl =$("#like-item-tpl").html();
				var func = Handlebars.compile(tpl);
				var result = func(rs.data.data);
				$("#like-item-container").html(result);
			}
		}else{
			//未登录直接跳转登录页面
			alert("您还未登录，请登录！");
			$(window).attr("location","login.html");
		}
	}
	
	

	return{
		ready:ready,
	};
})