define(['jquery','common'],function(jquery,common){

	//读取传递过来的订单编号
	var orderNo = common.getParam("orderNo");
	
	var total_price = 0;
	var total_points = 0;
		
	function ready(){
		$.ajax({
			url:baseUrl+"order/getdetail.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{"orderNo":orderNo},
			dataType:'json',
			success:function(rs){
				//判断方法是否成功
				if(rs.status == 0){
					//
					total_price=rs.data.amount;
					//插入各商品信息
					$("#orderInfo").html("订单编号："+rs.data.orderNum);
					$("#totalPrice").html("订单总额："+rs.data.amount+"&nbsp;元");
					$("#payPrice").html("&nbsp;"+rs.data.amount+"&nbsp;");
					$("#WIDout_trade_no").attr("value",rs.data.orderNum);
					$("#WIDsubject").attr("value","No."+rs.data.orderNum);
					$("#WIDtotal_amount").attr("value",rs.data.amount);
				}else{
					//失败弹出提示
					alert(rs.msg);
				}
			}
		});
	}
	
	//获取积分
	function getPoint(){
		$.ajax({
			url:baseUrl+"user/getpoint.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			success:function(rs){
				//判断方法是否成功
				if(rs.status == 0){
					//插入积分
					total_points = rs.data;
					$("#points").html(rs.data);
				}else{
					//失败弹出提示
					alert(rs.msg);
				}
			}
		});
	}
	$("#use_points").click(function (){
		
		var isUsePoint = $("#use_points").is(':checked');
		var money = 0;
		if(isUsePoint){
			var result = confirm("是否使用积分？");
			if(!result){
				return;
			}
			money=total_price - total_points*0.01;
			money=money.toFixed(2);
			$.ajax({
				url:baseUrl+"user/addpoint.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:{'points':-total_points},
				dataType:'json',
				success:function(rs){
					//判断方法是否成功
					if(rs.status == 0){
						//alert(rs.msg);
					}else{
						//失败弹出提示
						alert(rs.msg);
					}
				}
			});
		}else{
			var result = confirm("是否取消使用积分？");
			if(!result){
				return;
			}
			money=total_price;
			$.ajax({
				url:baseUrl+"user/addpoint.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				data:{'points':total_points},
				dataType:'json',
				success:function(rs){
					//判断方法是否成功
					if(rs.status == 0){
						//alert(rs.msg);
					}else{
						//失败弹出提示
						alert(rs.msg);
					}
				}
			});
		}
		$("#WIDtotal_amount").attr("value",money);
		$("#payPrice").html("&nbsp;"+money+"&nbsp;");
	});
	
	return{
		ready:ready,
		getPoint:getPoint,
	};
	
	
	
});