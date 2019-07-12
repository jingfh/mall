define(['jquery','ChineseDistricts','distpicker','handlebar','common'],
		function(jquery,distpicker_data,distpicker,Handlebars,common){
	common.getUserInfo();
	//读取传递过来的商品编号
	var pid = common.getParam("pid");
	
	var amount = 0;
	//1.获取商品数据
	//ready();
	function ready(){
		$('#distpicker').distpicker("destory");
		$('#distpicker').distpicker({autoSelect:false});
		$.ajax({
			url:baseUrl+"product/getdetail.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'productId':pid},
			dataType:'json',
			success:function(result){
				//数据加载成功时
				if(result.status == 0){
					//产品名称
					$("#product_name_container").html(result.data.name);
					//将产品id作为属性添加到product_name_container中
					$("#product_name_container").attr("data-id",result.data.id);
					//产品价格
					$("#productPriceContainer").html(result.data.price);
					//主图
					$("#mainImg").attr("src",baseUrl+result.data.icon_url);
					$("#productMainImage").attr("src",baseUrl+result.data.icon_url);
					$("#productMainImage").addClass(".product_picture_img");
		//alert("主图   "+ baseUrl+result.data.icon_url);
					//详情
					$("#detailContainer").html(result.data.detail);
					//规格参数
					$("#specParamContainer").html(result.data.specParam);
					//产品库存
					amount = result.data.stock;
					$("#product_num").attr("data-stock",result.data.stock);
					$("#stock_container").html("库存："+result.data.stock);
					//商品子图
					var subimages =result.data.sub_images;
					subimages = subimages.substring(0,subimages.length);
					//切割
					var images = subimages.split(",");
					var small_item ="";
					for(var i=0;i<images.length-1;i++){
//						small_item+="<li><img src=\'";
						images[i]=baseUrl+images[i];
//						small_item+=images[i];
//						small_item+="\'></li>";
						$("#productImage0"+(i+1)).css({display:"block"});
						$("#productImage0"+(i+1)).attr("src",images[i]);
					}
//					if(images.length<4){
//						for(var j=4-i;j<4;j++){
//							$("#productImage0"+(j)).css({display:"block"});
//						}
//					}
					//将子图插入页面
					$("#piclist_container").html();
					$("#piclist_container").append(small_item);
		//alert("小图   "+ small_item);
				}else{
					//数据加载失败
				}
			}
		});
	}
	
	//关注
	// jigfh 07.08
	$("#store_btn").click(function(){
		var result = confirm("是否关注该商品？");
		if(!result){
			return;
		}
		//请求服务器 加入关注列表
		$.ajax({
			url:baseUrl+"product/addlikeproducts.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'productId':pid},
			type:"post",
			dataType:'json',
			success:function(rs){
				//判断方法是否成功
				if(rs.status==0){
					//弹出提示消息
					alert(rs.msg);
					common.getUserInfo();
				}else{
					alert(rs.msg);
				}
			}
		});
	});
	
	
	//购买减少
	$(".product_minus_2").click(function(){
		//获取库存
		var stock = $("#product_num").attr("data-stock");
		var num = $("#product_num").val();
		//点击减少
		num = parseInt(num)-1;
		if(num <=0){
			num =0;
			alert("商品数量不能小于1！");
		}
		$("#product_num").val(num);
	});
	
	//购买数量增加
	$(".product_plus_1").click(function(){
		//获取库存
		var stock = $("#product_num").attr("data-stock");
		var num = $("#product_num").val();
		//点击增加
		num = parseInt(num)+1;
		if(num >stock){
			num =stock;
			alert("商品数量不能大于库存量！");
		}
		$("#product_num").val(num);
	});
	
	
	
	//立即购买
	$("#buy").click(function(){
		var result = confirm("是否立即购买？");
		if(!result){
			return;
		}
		//验证数量是否符合规范
		var count = $("#product_num").val();
		if(count <=0){
			alert("请填写正确购买数量，且不能小于1！");
			return;
		}
		if(count > amount){
			alert("请填写正确购买数量，且不能大于商品库存！");
			return;
		}
		$(window).attr("location","order_confirm.html?productId="+pid+"&quantity="+count);
	});
	
	
	
	
	//加入购物车
	$("#addCart").click(function(){
		var result = confirm("是否加入购物车？");
		if(!result){
			return;
		}
		//验证数量是否符合规范
		var count = $("#product_num").val();
		if(count <=0){
			alert("请填写正确购买数量，且不能小于1！");
			return;
		}
		if(count > amount){
			alert("请填写正确购买数量，且不能大于商品库存！");
			return;
		}
		//请求服务器 加入购物车
		$.ajax({
			url:baseUrl+"cart/savecart.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			data:{'productId':pid,'count':count},
			type:"post",
			dataType:'json',
			success:function(rs){
				//判断方法是否成功
				if(rs.status==0){
					//弹出提示消息
					alert(rs.msg);
					common.getUserInfo();
				}else{
					alert(rs.msg);
				}
			}
		});
	});
	//商品小图切换大图
	$(".product_picture_table_main ul li img").on("click",null,function(){
//alert("切换");
		//去掉其他图片的选中样式
		$(".product_picture_table_main ul li img").removeClass("product_picture_selected");
		$(this).addClass("product_picture_selected");
		//将小图放置到主图位置
		var imgSrc =$(this).attr("src");
		$(".product_picture_img").attr("src",imgSrc);	
	});

	
	//Tab页切换
	$(".product_tab_bar li").click(function(){
		$(".product_tab_bar li").removeClass("product_tab_selected");
		$(this).addClass("product_tab_selected");
		//获取点击tab页的index
		var index = $(this).attr("data-index");
		$(".product_tab_contents li").css("display","none");
		$(".product_tab_contents").find("li").eq(index).css("display","block");
	});
	
	//搜索商品
	$("#searchBtn").click(function(){
		var proName = $("#keyword").val();
		$(window).attr("location","product_search.html?name="+proName);
	});
	
	
	
	return{
		ready:ready
	};
	
	
	
});
