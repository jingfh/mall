require.config({
	 paths: {
			 "jquery": "/mall/js/jquery-1.6.2.min",
			 "handlebar": "/mall/js/handlebars-v4.0.11",
			 },
 });


require(['jquery','handlebar','common','order_list'],function (jquery,handlebar,common, order_list){
	$(function(){
		//加载登录用户信息
		common.getUserInfo();
		//加载积分
		common.getUserPoints();
		//加载积分
		common.getUserPoints();
		//用户登出
		common.loginOut();
		
		order_list.ready();
		
	});	
});