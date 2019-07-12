<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电脑网站支付return_url</title>
</head>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="cn.techaction.alipay.*"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.internal.util.*"%>
<%
/* *
 * 功能：支付宝服务器同步通知页面
 * 日期：2017-03-30
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


 *************************页面功能说明*************************
 * 该页面仅做页面展示，业务逻辑处理请勿在该页面执行
 */
 System.out.println("----------------------------return_url------------------------");
 
	//获取支付宝GET过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map<String,String[]> requestParams = request.getParameterMap();
	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		params.put(name, valueStr);
	}
	
	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

	//——请在这里编写您的程序（以下代码仅作参考）——
	if(signVerified) {
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
	
		//付款金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
		//跳转
		
		//取消订单
%>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">

var baseUrl = document.domain;
baseUrl= "http://"+baseUrl+":8080/mall/";
//alert("1111111111111111111111111111111111111");
function getParam(name){
	//构造一个含有目标参数的正则表达式对象
	var reg = new RegExp("(^|&)"+ name+"=([^&]*)(&|$)");
	//匹配目标参数
	var r = window.location.search.substr(1).match(reg);
	//返回参数值
	if(r !=null) return decodeURI(r[2]);return null;
}
var amount = getParam("total_amount");
var points = parseInt(amount*0.01);
	pointAjax = $.ajax({
		url:baseUrl+"user/addpoint.do",
		xhrFields:{withCredentials:true},
		crossDomain:true,
		data:{'points':points},
		dataType:'json',
		async:false, //同步
		success:function(rs){
			//判断方法是否成功
			if(rs.status==0){
				alert(rs.msg);
			}else{
				alert(rs.msg);
			}
		}
	});
var no = getParam("out_trade_no");
//alert(no);
	//function updateDataBase(){
	$.when(pointAjax).done(function () {
		$.ajax({
			url:baseUrl+"order/payedorder.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'orderNo':no},
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

</script>
<% 		
	}else {
		out.println("验签失败");
	}
	//——请在这里编写您的程序（以上代码仅作参考）——
%>
<body>
</body>
</html>