define(['jquery_SuperSlide','common','handlebar'],function(jquery_SuperSlide,common,Handlebars){
	//1.加载产品分类
	function getParam(){
		$.ajax({
			url:baseUrl+"param/findallparams.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			success:function(rs){
				//创建对象 预编译插件
				var tpl = $("#param_tpl").html();
				var func =Handlebars.compile(tpl);
				//获取数据
				var data = rs.data;
				var result = func(data);
				//插入页面
				$("#paramContainer").html(result);
			}
		});
	}
	//2.加载热销商品
	function getHotProduct(){
		$.ajax({
			url:baseUrl+"product/findhotproducts.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			success:function(rs){
				//创建顶部搜索框 热销对象 预编译
				var tpl_1 =$("#hot_tag_tpl").html();
				var func_1 =Handlebars.compile(tpl_1);
				//创建中部热销栏 对象 预编译
				var tpl =$("#hot_tpl").html();
				var func =Handlebars.compile(tpl);
				
				//获取数据 处理数据（图片）
				var data = new Array();
				for(var i=0;i<rs.data.length;i++){
					rs.data[i].iconUrl=baseUrl+rs.data[i].icon_url;
					data[i]=rs.data[i];
					if(i>=3){
						//前台只展示4条
						break;
					}
				}
				var data_1 = new Array();
				for(var i=0;i<data.length;i++){
					data_1[i]=data[i];	
					if(i>=2) break;
				}
				
				//添加数据插入页面 css样式修改
				var result_1= func_1(data_1);
				$("#hotpro").html("热销:"+result_1);
				
				
				var result= func(data);
				$("#hotContainer").html(result);
				//为最后一个li添加样式
				$("#hotContainer>li:last-child").add("right_border");
			}
		});
	}
	//3.加载楼层信息
	function getFloors(){
		$.ajax({
			url:baseUrl+"product/findfloors.do",
			xhrFields:{withCredentials:true},
			crossDomain:true,
			dataType:'json',
			success:function(rs){
				//判断是否成功
				if(rs.status==1){
					return;
				}
				//1楼数据      获取数据（修改图片路径） 插件预编译  插入页面
				var data1 = rs.data.oneFloor;
				for(var i=0;i<data1.length;i++){
					data1[i].iconUrl=baseUrl+data1[i].icon_url;
				}
				var data1_m=new Array();
				var data1_r=new Array();
				for(var i=0;i<data1.length;i++){
					if(i<2){
						data1_m[i]=data1[i];
					}
					if(i>=2 && i<8){
						data1_r[i]=data1[i];
					}
				}
	//alert(data1_m.length);
	//alert(data1_r.length);
				var func = Handlebars.compile($("#floor_odd_top").html());
				$("#floor_middle_box01_container").html();
				$("#floor_middle_box01_container").append(func(data1_m));
				$("#floor_right_box01_container").html();
				$("#floor_right_box01_container").append(func(data1_r));
				//2楼数据
				var data2 = rs.data.twoFloor;
				for(var i=0;i<data2.length;i++){
					data2[i].iconUrl=baseUrl+data2[i].icon_url;
				}
				var data2_m=new Array();
				var data2_r=new Array();
				for(var i=0;i<data2.length;i++){
					if(i<3){
						data2_m[i]=data2[i];
					}
					if(i>=3 && i<9){
						data2_r[i]=data2[i];
					}
				}
				$("#floor_middle_box02_container").html();
				$("#floor_middle_box02_container").append(func(data2_m));
				$("#floor_right_box02_container").html();
				$("#floor_right_box02_container").append(func(data2_r));
				//3楼数据
				var data3 = rs.data.threeFloor;
				for(var i=0;i<data3.length;i++){
					data3[i].iconUrl=baseUrl+data3[i].icon_url;
				}
				var data3_m=new Array();
				var data3_r=new Array();
				for(var i=0;i<data3.length;i++){
					if(i<2){
						data3_m[i]=data3[i];
					}
					if(i>=2 && i<8){
						data3_r[i]=data3[i];
					}
				}
				$("#floor_middle_box03_container").html();
				$("#floor_middle_box03_container").append(func(data3_m));
				$("#floor_right_box03_container").html();
				$("#floor_right_box03_container").append(func(data3_r));
				//4楼数据
				var data4 = rs.data.fourFloor;
				for(var i=0;i<data4.length;i++){
					data4[i].iconUrl=baseUrl+data4[i].icon_url;
				}
				var data4_m=new Array();
				var data4_r=new Array();
				for(var i=0;i<data4.length;i++){
					if(i<3){
						data4_m[i]=data4[i];
					}
					if(i>=3 && i<9){
						data4_r[i]=data4[i];
					}
				}
				$("#floor_middle_box04_container").html();
				$("#floor_middle_box04_container").append(func(data4_m));
				$("#floor_right_box04_container").html();
				$("#floor_right_box04_container").append(func(data4_r));
			}
		});
	}
	
	//4.首页轮播
	$(".slide_box").slide({mainCell:".bd ul" ,effect:"leftLoop",autoPlay:true});
	
	//5.产品分类
	$(".iten").live({
		mouseenter:function(){
			//获取对象
			var children_div = $(this).children("div");
			var t_this = $(this);
			//显示子分类
			t_this.find('.item_hd').css({border:'none'});
			t_this.prev().find('.item_hd').css({border:'none'});
			children_div.show();
			$(this).addClass("selected");
		},
		mouseleave:function(){
			//获取对象
			var children_div = $(this).children("div");
			//隐藏子分类
			$(this).find('.item_hd').css({'border-bottom':'1px dotted white'});
			$(this).prev().find('.item_hd').css({'border-bottom':'1px dotted white'});
			children_div.hide();
			$(this).removeClass("selected");
		}
	});
	
	//单击搜索
	$("#searchBtn").click(function(){
		var proName = $("#keyword").val();
		$(window).attr("location","product_search.html?name="+proName);
	});
	
	return{
		getParam:getParam,
		getHotProduct:getHotProduct,
		getFloors:getFloors
	};
});