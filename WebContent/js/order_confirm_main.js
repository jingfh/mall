require.config({
	 paths: {
			 "jquery": "/mall/js/jquery-1.6.2.min",
			 "jquery_SuperSlide": "/mall/js/jquery.SuperSlide.2.1.1",
			 "handlebar": "/mall/js/handlebars-v4.0.11",
			 },
	 shim:  {
			 'jquery_SuperSlide':['jquery']
	 		}
 });

require(['jquery','jquery_SuperSlide','handlebar','common','address','order_confirm'],function (jquery,jquery_SuperSlide,handlebar,common, address,order_confirm){
	$(function(){
		//加载登录用户信息
		common.getUserInfo();
		//加载积分
		common.getUserPoints();
		//用户登出
		common.loginOut();
		
		//加载当前用户的地址信息
		address.getUserAddrInfo();
		//删除地址
		address.delAddress();
		//设为默认地址
		address.defaultAddress();
		
		//读取传递过来的商品信息
		var pid = common.getParam("productId");
		var count = common.getParam("quantity");
		if(pid!=null && count!=null){
			order_confirm.getProductInfo(pid,count);
			order_confirm.submitBtnPro(pid,count);
		}else{
			//读取用户生成的订单购物车商品信息
			order_confirm.getCartInfo();
			//提交确认信息生成订单
			order_confirm.submitBtn();
		}
		
	});	
});