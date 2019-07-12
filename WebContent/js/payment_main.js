require.config({
	 paths: {
			 "jquery": "/mall/js/jquery-1.6.2.min",
			 "handlebar": "/mall/js/handlebars-v4.0.11",
			 //"distpicker_data":"/mall/js/distpicker.data",
			 //"distpicker":"/mall/js/distpicker",
			 }
 });

require(['jquery','handlebar','common','payment'],function (jquery,handlebar,common, payment){
	$(function(){
		
		payment.ready();
		payment.getPoint();
	});	
});