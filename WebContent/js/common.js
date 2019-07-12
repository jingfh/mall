var baseUrl = document.domain;
baseUrl= "http://"+baseUrl+":8080/mall/";



define(function(){
	//获取url中的参数
	/*
	 * 加载商品分类信息
	 * */
	
	function getParam(name){
		//构造一个含有目标参数的正则表达式对象
		var reg = new RegExp("(^|&)"+ name+"=([^&]*)(&|$)");
		//匹配目标参数
		var r = window.location.search.substr(1).match(reg);
		//返回参数值
		if(r !=null) return decodeURI(r[2]);return null;
	}
	//加载登录用户信息
	function getUserInfo(){
		//向服务器请求数据
		$.ajax({
			url:baseUrl+"user/do_get_info.do",
			xhrFields:{withCredentials:true},//允许跨域请求携带信息
			crossDomain:true,//允许跨域请求
			dataType:'json',
			success:function(user){
				//判断是否成功
				if(user.status == 0){
					//隐藏登录时span标签
					$("#register-info").css({display:"none"});
					//显示登陆后span标签
					$("#login-info").css({display:"block"});
//					var item=document.getElementById("login-info").getElementsById("headerUsername");
//					item.href="information_modification.html";
					//将用户名添加
					$("#headerUsername").html("&nbsp;&nbsp;&nbsp;&nbsp;"+user.data.account+"&nbsp;&nbsp;");
//					个人信息左侧栏加入姓名
//					alert(user.data.account);
					$("#user_name").html(" 欢迎回来，"+user.data.account+"&nbsp;&nbsp;");
					$("#left_username").html(user.data.account);
					//获取用户购物车商品数量
					getCartCount();
				}
			}
		});
	}
	//获取用户购物车商品数量
	function getCartCount(){
		$.ajax({
			url:baseUrl+"cart/getcartcount.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			success:function(rs){
				//判断是否成功
				if(rs.status==0){
					//插入数据
					$("#cartQuantity").html("[" +rs.data + "]");
				}
			}
		});
	}
	//用户登出
	function loginOut(){
		//给退出按钮挂上单击事件
		$("#headerLogout").click(function(){
			//向服务器请求数据
			$.ajax({
				url:baseUrl+"user/do_logout.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				dataType:"json",
				success:function(rs){
					console.log(rs);
					console.log("用户登出");
					 if(rs==3){
						//显示登录时span标签
//						 console.log("显示登录时span标签");
						$("#register-info").css({display:"block"});
						//隐藏登陆后span标签
//						 console.log("隐藏登陆后span标签");
						$("#login-info").css({display:"none"});
						//清空购物车数量
//						 console.log("清空购物车数量");
						$("#cartQuantity").html("[0]");
//						掩盖用户名
//						 console.log("掩盖用户名");
						$("#headerUsername").css({display:"none"});
					 }
				}
			});
		});
	}
	
	
	/*
	 * jigfh
	 * 2019.07.09
	 */
	//获取用户积分信息
	function getUserPoints(){
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
					$("#left_userpoints").html(rs.data);
				}else{
					//失败弹出提示
//					alert(rs.msg);
					$(window).attr("location","login.html");
				}
			}
		});
	}
	
	
	//获取用户购物车商品数量
		function getRootPath(){
			$.ajax({
				url:baseUrl+"user/getRootPath.do",
				xhrFields:{withCredentials:true},
				crossDomain:true,
				dataType:'json',
				success:function(rs){
					//判断是否成功
					if(rs.status==0){
						alert("路径："+rs.msg);
					}
				}
			});
		}
	
	
	
	return{
		getParam:getParam,
		getUserInfo:getUserInfo,
		getUserPoints:getUserPoints,
		getCartCount:getCartCount,
		loginOut:loginOut,
		getRootPath:getRootPath
	};
});