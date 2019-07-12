define(['jquery','common'],function(jquery,common){
	/*
	//姓名是否有效
	var isNameValidate = false;
	//年龄是否有效
	var isAgeValidate = false;
	//用户电子邮箱是否有效
	var isEmailValidate = false;
	//用户手机号是否有效
	var isPhoneValidate = false;
	*/
	//显示当前用户信息
	showUser();
	
	//1.姓名验证
	$("#name").blur(function(){
		var name = $("#name").val();
		$("#nameError").css({display:"none"});
		//验证用户名是否为空
		if(name == ""){
			$("#nameError").html("请输入姓名！");
			$("#nameError").css({display:"block"});
			return ;
		}
	});
	
	//2.验证年龄非空、范围、必须为数字
	$("#age").blur(function(){
		var age = $("#age").val();
		$("#ageError").css({display:"none"});
		//验证用户名是否为空
		if(age == ""){
			$("#ageError").html("请输入年龄！");
			$("#ageError").css({display:"block"});
			return ;
		}
		var re = /^[0-9]+.?[0-9]*$/;
		if(!re.test($("#age").val())){
			$("#ageError").html("年龄必须为数字");
			$("#ageError").css("display","block");
			return ;
		}
		if($("#age").val()<0 || $("#age").val()>120){
			$("#ageError").html("年龄在0~120岁之间！");
			$("#ageError").css("display","block");
			return ;
		}
	});
		
		
	//3.验证电话非空、格式
	$("#phone").blur(function(){
		var phone = $("#phone").val();
		$("#phoneError").css({display:"none"});
		//验证用户名是否为空
		if(phone == ""){
			$("#phoneError").html("请输入手机号！");
			$("#phoneError").css({display:"block"});
			return;
		}
		var phone = $("#phone").val();
		var reg= /^1[0-9]{10}$/;
		if(!reg.test(phone)){
			$("#phoneError").css("display","block");
			$("#phoneError").html("电话号码格式不正确！");
			return;
		}
	});	
		

	//4.验证email非空、格式
	$("#email").blur(function(){
		var email = $("#email").val();
		$("#emailError").css({display:"none"});
		//验证用户名是否为空
		if(email == ""){
			$("#emailError").html("请输入手机号！");
			$("#emailError").css({display:"block"});
			return;
		}
		var email = $("#email").val();
		var reg= /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		if(!reg.test(email)){
			$("#emailError").css("display","block");
			$("#emailError").html("邮箱格式不正确！");
			return;
		}
	});	
	
	
	//5.保存添加事件
	$("#saveBtn").click(function(){
		var result = confirm("是否确认修改个人信息？");
		if(!result){
			return;
		}
		//alert("验证通过。。。。");
		var sex="男";
		//alert($("input:radio[name='sex']:checked").val());
		if($("input:radio[name='sex']:checked").val()  ==  '女'){
			sex="女";
		}
		//提交后台修改
		var formData={
				name:$("#name").val(),
				sex:sex,
				age:$("#age").val(),
				phone:$("#phone").val(),
				email:$("#email").val()
		};
		$.ajax({
			url:baseUrl+"user/updateuserinfo.do",
			data:formData,
			dataType:'json',
			xhrFields:{withCredentials:true},
			crossDomain:true,
			type:"post",
			success:function(rs){
				alert(rs.msg);
				//showUser();
			}
		});
	});
		
		
		
		
		//单击搜索
		$("#searchBtn").click(function(){
			var proName = $("#keyword").val();
			$(window).attr("location","product_search.html?name="+proName);
		});
		
	
		
	
	//显示当前用户信息
	function showUser(){
		$.ajax({
			url:baseUrl+"user/do_get_info.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			//async:false, //同步
			success:function(rs){
				//判断石是否登陆成功
				if(rs.status == 0){
					//显示用户信息
					$("#username").val(rs.data.account);
					//$("#account").attr("disabled","disabled");
					$("#username").attr("readonly",true);
					$("#name").val(rs.data.name);
					$("#age").val(rs.data.age);
					$("#phone").val(rs.data.phone);
					$("#email").val(rs.data.email);			
					if(rs.data.sex==1){
						$("input:radio[name='sex']").get(0).checked=true;
					}else{
						$("input:radio[name='sex']").get(1).checked=true;
					}
				}else{
					//alert(rs.msg);
					alert("您未登录，请登录");
					$(window).attr("location","login.html");
				}
			}
		});
		
	}
	
	/*
	return{
		showUser:showUser,
		save_Btn:save_Btn,
	};
	*/
	
});