require.config({
	 paths: {
			 "jquery": "/mall/js/jquery-1.6.2.min",
			 "handlebar": "/mall/js/handlebars-v4.0.11",
			 }
 });

require(['jquery','handlebar','common','cart'], function (jquery,handlebar,common, cart){
	$(function(){
		//加载用户登陆信息
		common.getUserInfo();
		//加载积分
		common.getUserPoints();
		//用户登出
		common.loginOut();
		
		cart.ready();
	});	
});