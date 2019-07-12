define(['jquery_SuperSlide','handlebar'],function(jquery,Handlebars){
	
	
	function getProductInfo(pid,count){
		$.ajax({
			url:baseUrl+"product/getdetail.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'productId':pid},
			dataType:'json',
			success:function(rs){
				//数据加载成功时
				if(rs.status == 0){
					$("#order_confirm_item_container").html();
					var tpl=$("#buynow-product-item-tpl").html();
					var func =Handlebars.compile(tpl);
					//rs.data+={"quantity":count};
		console.log(rs.data);
					var result=func(rs.data);
					$("#order_confirm_item_container").html(result);
					$("#quantity").html("x&nbsp;"+count);
					//更新购物车总价格
					$("#amount").html("￥"+(rs.data.price*count));
				}else{
					//数据加载失败
					alert(rs.msg);
				}
			}
		});
	}
	//提交确认信息生成订单
	function submitBtnPro(pid,count){
		$("#submit").click(function(){
			var result = confirm("是否确认提交订单？");
			if(!result){
				return;
			}
			//收货人地址ID
			var id =$(this).attr("data-id");
			//提交订单
			$.ajax({
				url:baseUrl+"order/createorderProduct.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:{"addrId":id,"productId":pid,"quantity":count},
				dataType:'json',
				type:"post",
				success:function(rs){
					if(rs.status==0){
						//跳转到订单详情页面
						$(window).attr("location","order_detail.html?orderNo="+rs.data.orderNum);
					}else{
						alert(rs.msg);
					}
				}
			});
		});
	}
	
	//读取用户生成的订单购物车商品信息
	function getCartInfo(){
		$.ajax({
			url:baseUrl+"cart/findallcartsChecked.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			success:function(rs){
				//更新页面
				updatePageInfo(rs);
			}
		});
	}

	//提交确认信息生成订单
	function submitBtn(){
		$("#submit").click(function(){
			var result = confirm("是否确认提交订单？");
			if(!result){
				return;
			}
			//收货人地址ID
			var id =$(this).attr("data-id");
			//提交订单
			$.ajax({
				url:baseUrl+"order/createorderChecked.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:{"addrId":id},
				dataType:'json',
				type:"post",
				success:function(rs){
					if(rs.status==0){
						//跳转到订单详情页面
						$(window).attr("location","order_detail.html?orderNo="+rs.data.orderNum);
					}else{
						alert(rs.msg);
					}
				}
			});
		});
	}
	
	
	//根据返回数据更新页面信息
	function updatePageInfo(rs){
		if(rs.status ==0){
			//信息更新
			$("#order_confirm_item_container").html();
			var tpl=$("#product-item-tpl").html();
			var func =Handlebars.compile(tpl);
			var result=func(rs.data.lists);
			$("#order_confirm_item_container").html(result);
			//更新购物车总价格
			$("#amount").html("￥"+rs.data.totalPrice);
		}else{
			//未登录直接跳转登录页面
			$(window).attr("location","login.html");
		}
	}
	
	return{
		getCartInfo:getCartInfo,
		submitBtn:submitBtn,
		getProductInfo:getProductInfo,
		submitBtnPro:submitBtnPro
	};
});