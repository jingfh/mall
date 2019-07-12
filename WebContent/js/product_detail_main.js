require.config({
	 paths: {
			 "jquery": "/mall/js/jquery-1.11.3.min",
			 "handlebar": "/mall/js/handlebars-v4.0.11",
			 "ChineseDistricts": "/mall/js/jqueryDistpicker/distpicker.data",
			 "distpicker":"/mall/js/jqueryDistpicker/distpicker",
			 },
	shim:  {
		'distpicker': ['jquery','ChineseDistricts'],
		}
 });

require(['jquery','handlebar','common','product_detail'],
		function (jquery,handlebar,common,product_detail){
	$(function(){
		//加载用户登陆信息
		common.getUserInfo();
		//用户登出
		common.loginOut();
		product_detail.ready();
		
	});	
});