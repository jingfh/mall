require.config({
	 paths: {
			 "jquery": "/mall/js/jquery-1.11.3.min",
			 "handlebar": "/mall/js/handlebars-v4.0.11",
			 "ChineseDistricts": "/mall/js/jqueryDistpicker/distpicker.data",
			 "distpicker": "/mall/js/jqueryDistpicker/distpicker",
			 },
	 shim:  {
		 	'distpicker': ['jquery','ChineseDistricts'],
	 		}
 });

require(['jquery','handlebar','common','address_management'], 
		function (jquery,handlebar,common, address_management){
	$(function(){
		//加载登录用户信息
		common.getUserInfo();
		//加载积分
		common.getUserPoints();
		//用户登出
		common.loginOut();
		
		address_management.ready();
	});	
});