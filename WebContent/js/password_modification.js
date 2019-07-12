define(['jquery','common'],function(jquery,common){
	
	//当前
	var isNowPwdValidate = false;
	//新
	var isNewPwdValidate = false;
	//确认-新
	var isRePwdValidate = false;
		
	
	//function ready(){
		//1.当前密码失去焦点验证
		$("#now_password").blur(function(){
			var pwd = $("#now_password").val();
			isNowPwdValidate = checkPwd(pwd);
			//checkNowPwd(pwd);
		});
		function checkPwd(pwd){
			//创建对象获取输入框内容
			
			$("#nowPwdError").css({display:"none"});
			//校验密码是否为空
			if(pwd =="" || pwd == null ){
				$("#nowPwdError").html("请输入当前密码！");
				$("#nowPwdError").css({display:"block"});
				return false;
			}
			//验证密码长度
			if(pwd.length<6 || pwd.length>12){
				$("#nowPwdError").html("密码长度为6-12位！");
				$("#nowPwdError").css({display:"block"});
				return false;
			}
			//验证密码格式
			var reg = /^[0-9a-zA-Z]+$/;
			var str = document.getElementById("now_password").value;
			if(!reg.test(str)){
				$("#nowPwdError").html("密码只能为数字和英文！");
				$("#nowPwdError").css({display:"block"});
				return false;
			}
			
			$("#nowPwdError").css("display","none");
//alert("1.当前密码校验通过");
			return true;
		}
		function checkNowPwd(pwd){
			$("#nowPwdError").css({display:"none"});
		alert("pwd="+pwd);
			$.ajax({
				url:baseUrl+"user/checkpassword.do",
				//xhrFields:{withCredentials:true},
				//crossDomain:true,
				type:"post",
				data:{"pwd":pwd},
				dataType:'json',
				//async:false,//同步
				success:function(data){
					alert("test");
					$("#nowPwdError").css({display:"none"});
					if(data.status == 1){
						alert(rs.msg);
						$("#nowPwdError").html("当前密码错误！");
						$("#nowPwdError").css({display:"block"});
						//isNowPwdValidate =  false;
						
					}	
				}
			});
			//$("#nowPwdError").css({display:"none"});
			return true;
		}
		
		
		
		
		//新密码的非空验证，长度，组成字符验证
		$("#new_password").blur(function(){
			isNewPwdValidate = checkNewPwd();
		});
		function checkNewPwd(){
			//创建对象获取输入框内容
			var pwd = $("#new_password").val();
			$("#newPwdError").css({display:"none"});
			//校验密码是否为空
			if(pwd =="" || pwd == null ){
				$("#newPwdError").html("请输入新密码！");
				$("#newPwdError").css({display:"block"});
				return false;
			}
			//验证密码长度
			if(pwd.length<6 || pwd.length>12){
				$("#newPwdError").html("密码长度为6-12位！");
				$("#newPwdError").css({display:"block"});
				return false;
			}
			//验证密码格式
			var reg = /^[0-9a-zA-Z]+$/;
			var str = document.getElementById("new_password").value;
			if(!reg.test(str)){
				$("#newPwdError").html("密码只能为数字和英文！");
				$("#newPwdError").css({display:"block"});
				return false;
			}
			//$("#newPwdError").css("display","none");
//alert("2.新密码校验通过");
			return true;
		}
		
		
		
		
		
		//确认密码非空验证，长度 ，组成    两次密码是否一致
		$("#password_confirmation").blur(function(){
			isRePwdValidate = checkRePwd();
		});
		function checkRePwd(){
			//创建对象获取输入框内容
			var pwd = $("#password_confirmation").val();
			$("#rePwdError").css({display:"none"});
			//校验密码是否为空
			if(pwd =="" || pwd == null ){
				$("rePwdError").html("请重新输入新密码！");
				$("#rePwdError").css({display:"block"});
				return false;
			}
			//判断两次密码是否一致
			if($("#password_confirmation").val()!=$("#new_password").val()){
				$("#rePwdError").css("display","block");
				$("#rePwdError").html("两次密码不一致！");
				return false;
			}
			//$("#rePwdError").css("display","none");
//alert("3.确认密码校验通过");
			return true;
		}
		
		
		
		
		
		
		
		
		
		//保存按钮事件
		$("#saveBtn").click(function(){
			var result = confirm("是否修改密码？");
			if(!result){
				return;
			}
			
			//alert(1);
			if(!isNowPwdValidate){
				return checkPwd($("#now_password").val());
			}
			if(!isNewPwdValidate){
				alert(validatePwd("new_password"));
				return checkNewUserPwd();
			}
			if(!isRePwdValidate){
				//alert(validatePwd("re_password"));
				return checkReUserPwd();
			}
			
			$.ajax({
				url:baseUrl+"user/updatepassword.do",
				//xhrFields:{withCredentials:true},
				//crossDomain:true,
				type:"post",
				data:{"newpwd":$("#password_confirmation").val(),"oldpwd":$("#now_password").val()},
				dataType:'json',
				success:function(data){
					//弹出提示信息
	
					//alert(data.status);
					//判断是否成功 成功则跳转登录页面
					if(data.status == 0){
alert("4.修改密码成功");
						$(window).attr("location","login.html");
					}else{
						alert(data.msg);
					}	
				}
			});
		});
		
	//}
	
	//验证密码的非空长度和组成
	function validatePwd(labelId){
		//获取密码
		var pwd = $("#"+labelId).val();
		//非空判断
		if(pwd==null||pwd==""){
			return "密码不能为空！";
		}
		//长度判断
		if(pwd.length<6||pwd.length>12){
			return "密码长度为6到12位！";
		}
		//组成判断
		var reg =/^[0-9a-zA-Z]+$/; //正则
		if(!reg.test(pwd)){
			return "密码只能为数字和英文！";
		}
		return null;
	}
	/*
	return{
		ready:ready
	};
	*/
});