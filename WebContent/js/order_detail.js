define(['jquery','handlebar','common'],function(jquery,Handlebars,common){
	//1.获取订单编号
	var orderNo = common.getParam("orderNo");
//	测试用orderID 
//	var orderNo=1519099875344;
//	alert(orderNo);
	//2.获取订单详情
	function getDetail(){
		$.ajax({
			url:baseUrl+"order/getdetail.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{"orderNo":orderNo},
			dataType:'json',
			success:function(rs){
				//判断方法是否正确
				if(rs.status==0){
//					alert(orderNo);
					//订单信息
					$("#ordernum-container").html(rs.data.orderNum);
					$("#order-created-container").html(rs.data.created);
					$("#order-price-container").html(rs.data.amount);
					$("#order-status-container").html(rs.data.statusDesc);
					$("#order-ptype-container").html(rs.data.type);
					$("#order-paytime-container").html(rs.data.paymentTime);
					$("#order-delivertime-container").html(rs.data.deliveryTime);
					$("#order-receivetime-container").html(rs.data.finishTime);
					$("#order-finishtime-container").html(rs.data.closeTime);
					//拼接地址信息
					var address = rs.data.address.province+" "
					 	+rs.data.address.city+" "
					 	+rs.data.address.addr+" ";
//					console.log(address);
					$("#consignee_container").html(rs.data.address.name);
					$("#phone_container").html(rs.data.address.mobile);
					$("#address_container").html(address);
					//商品订单列表
					$("#item-container").html();
					var tpl = $("#product-item-tpl").html();
					var func = Handlebars.compile(tpl);
					var result = func(rs.data.orderItems);
					$("#item-container").html(result);
					//支付 取消 确认收货按钮显示判断
					if(rs.data.status != 1){
						$("#order_pay").remove();
						$("#order_cancle").remove();
					}
					if(rs.data.status != 4){
						$(".opertion").remove();
						$("#comment_box").remove();
					}
					if(rs.data.status ==4){
						for(var i=0;i<rs.data.orderItems.length;i++){
							var product_id=rs.data.orderItems[i].goodsId;
							if(rs.data.orderItems[i].comment != null){
								$("#comment_on").remove();
								$(".ordercancle"+product_id).html(rs.data.orderItems[i].comment);
							}
							else{
								$(".ordercancle"+product_id).remove();
							}
						}
//						console.log(rs.data.orderItems[0].comment);
//						$("#comment_box").remove();
					}
				}else{
					alert(rs.msg);
				}
			}
		});
	}
	
	//点击取消订单按钮
	//function orderCancel(){
		//取消按钮挂单击事件
		$("#order_cancel").click(function(){
			var result = confirm("是否取消订单？");
			if(!result){
				return;
			}
			$.ajax({
				url:baseUrl+"order/cancelorder.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:{'orderNo':orderNo},
				dataType:'json',
				success:function(rs){
					//判断方法是否成功
					if(rs.status==0){
						alert(rs.msg);
						$(window).attr("location","order_list.html");
					}else{
						alert(rs.msg);
					}
				}
			});
			
		});
	//}
	//点击支付按钮
		//function orderPay(){
			//支付按钮挂单击事件
			$("#order_pay").click(function(){
				var result = confirm("是否支付？");
				if(!result){
					return;
				}
				$(window).attr("location","payment.html?orderNo="+orderNo);
			});
		//}
	
			
			//评论按钮挂单击事件
			$(".commentbtn").live('click',function(){
				var result = confirm("是否进行评论？");
				if(!result){
					return;
				}
				var text = document.getElementById("comment_text").value;
				if(text==null || text==""){
					alert("评论内容不能为空。");
					return ;
					//$(window).attr("location","order_detail.html"+"?orderNo="+orderNo);
				}
				var pno = $(this).attr("data-product-id");
				$.ajax({
					url:baseUrl+"order/addComment.do",
					xhrFields:{withCredentials:true},
					crossDomain:true,
					data:{'orderNo':orderNo,'productId':pno,'text':text},
					dataType:'json',
					success:function(rs){
						//判断方法是否成功
						if(rs.status==0){
							alert(rs.msg);
							//$(this).remove();
							$("#comment_text").html();
							$(window).attr("location","order_detail.html"+"?orderNo="+orderNo);
						}else{
							
							
							alert(rs.msg);
						}
					}
				});
			});
			
			
	//单击搜索
	$("#searchBtn").click(function(){
		var proName = $("#keyword").val();
		$(window).attr("location","product_search.html?name="+proName);
	});
	
	return{
		getDetail:getDetail,
		//orderCancel:orderCancel,
		//orderPay:orderPay
	};
});