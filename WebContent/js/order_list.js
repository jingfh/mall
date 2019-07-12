define(['jquery','common','handlebar'],function(jquery,common,Handlebars){
	
	//读取传递过来的订单支付状态
	//var paymentStatus = common.getParam("paymentStatus");
//alert("paymentStatus = "+paymentStatus);

//方法


	
	var status= 0;//订单状态
	var pageNum =1;//当前页
	var pageSize = 5;//一页的数量
	function ready(){
		//点击全部订单 待付款 待收货 事件
		$(".nav_item").live("click",function(){
			//获得标签位置拿到状态
			status = $(this).attr("data-status");
			//清除选中样式
			$(".nav_item").removeClass("on");
			//添加点击标签的选中样式
			$(this).addClass("on");
			//获取订单信息
			pageNum = 1;
			getOrders(status,pageNum,pageSize);
		});
		
		//绑定分页点击事件
		$(".btn_prev").click(function(){
			//获取上一页的页码
			pageNum =$(".btn_prev").attr("data-page");
			getOrders(status,pageNum,pageSize);
		});
		$(".btn_next").click(function(){
			//获取下一页的页码
			pageNum =$(".btn_next").attr("data-page");
			getOrders(status,pageNum,pageSize);
		});
		
		//确认收货
		$(".confirm_receipt3").live("click",function(){
			var result = confirm("是否确认收货？");
			if(!result){
				return;
			}
			//获取订单号
			var orderNo = $(this).attr("data-orderno");
//alert(""+orderNo);
			//向服务器发送请求
			$.ajax({
				url:baseUrl+"order/confirmreceipt.do",
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
		
		//取消订单
		$(".cancelRecipt1").live("click",function(){
			var result = confirm("是否取消订单？");
			if(!result){
				return;
			}
			//获取订单号
			var orderNo = $(this).attr("data-orderno");
//alert(""+orderNo);
			//向服务器发送请求
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
		
		//获取订单列表
		getOrders(1,1,5,function(){
			getOrders(2,1,5,function(){
				getOrders(3,1,5,function(){
					getOrders(0,1,5,null);
				})
			})
		})
	}
	//单击搜索
	$("#searchBtn").click(function(){
		var proName = $("#keyword").val();
		$(window).attr("location","product_search.html?name="+proName);
	});
	
	//获得订单列表
	function getOrders(status,pageNum,pageSize,callBack){
		$.ajax({
			url:baseUrl+"order/getlist.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'status':status,'pageNum':pageNum,'pageSize':pageSize},
			dataType:'json',
			success:function(rs){
				//方法成功时
				if(rs.status==0){
//alert("getlist成功");
					//内容嵌入
					$("#order-list-container").html();
					var tpl= $("#order-item-tpl").html();
					var func = Handlebars.compile(tpl);
					var result=func(rs.data.data);
					$("#order-list-container").html(result);
					//处理分页显示
					$(".btn_prev").attr("data-page",rs.data.prePage);
					$(".btn_next").attr("data-page",rs.data.nextPage);
					$(".page_num").attr("data-page-num",rs.data.pageNum);
					$(".page_num").html(rs.data.pageNum);
					$(".page_count").html("共"+rs.data.totalPage+"页");
					//各种状态的订单数量显示
					//全部订单
					if(status==0){
						$("#all_num").html("("+rs.data.totalRecord+")");
						//修改立即付款按钮（除未付款状态）
						$(".pay_order"+3).html("查看订单");
						$(".pay_order"+4).html("查看订单");
						$(".pay_order"+6).html("查看订单");
						$(".pay"+6).remove();
						$(".confirm_receipt"+6).remove();
						$(".cancelRecipt"+6).remove();
//						完成订单
						$(".pay"+4).remove();
						$(".confirm_receipt"+4).remove();
						$(".cancelRecipt"+4).remove();
//						未付款
						$(".confirm_receipt"+1).remove();
//						已付款
						$(".pay"+2).remove();
						$(".confirm_receipt"+2).remove();
						$(".cancelRecipt"+2).remove();
//						已发货
						$(".pay"+3).remove();
						$(".cancelRecipt"+3).remove();
					}
					//待付款
					if(status==1){
						$(".confirm_receipt"+1).remove();
						$("#no_pay_num").html("("+rs.data.totalRecord+")");
					}
					
//					已付款
					if(status==2){
						$(".pay"+2).remove();
						$(".confirm_receipt"+2).remove();
						$(".cancelRecipt"+2).remove();
						$("#payed_num").html("("+rs.data.totalRecord+")");
					}
					//待收货
					if(status==3){
						$("#unshipped_num").html("("+rs.data.totalRecord+")");
						//清除立即付款，显示确认收货
						$(".pay"+3).remove();
						$(".cancelRecipt"+3).remove();
						//$(".confirm_receipt").attr("style","display:block");
						$(".confirm_receipt").css({"display":"block","margin-left": "93px"});
						/*$(".confirm_receipt").css({"display":"block",
							"background-color": "#41b5e7","color": "#fff","box-sizing": "border-box",
							//display: inline-block;
							"border": "none","height": "28px","line-height": "26px",
							"padding": "0 9px","border-radius": "3px","font-size": "12px","cursor": "pointer",
							//"margin-left": "87px","margin-top": "-15px"
						});*/
					}
//					交易完成
					if(status==4){
						
						$(".pay"+4).remove();
						$(".confirm_receipt"+4).remove();
						$(".cancelRecipt"+4).remove();
						$("#finished_num").html("("+rs.data.totalRecord+")");
					}
					
				}	
				//回调函数
				if(callBack) callBack();
			}
		});
	}
	
	return{
		ready:ready
	};
	
});