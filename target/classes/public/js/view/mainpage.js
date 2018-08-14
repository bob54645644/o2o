$(function(){
	var url="/view/mainpageinfo";
	
	$.getJSON(url,function(data){
		if(data.success){
			var headLineList = data.headLineList;
			var swiperHtml = '';
			headLineList.map(function(item,index){
				swiperHtml +='<div class="swiper-slide">'
					+'<a href="'+item.lineLink+'">'
					+'<img src="/image'+item.lineImg+'" alt="'
					+item.lineName+'"></a></div>';
			});
			//轮播图赋值给前端HTML控件
			$(".swiper-wrapper").html(swiperHtml);
			
			//轮播图设置
			$(".swiper-container").swiper({
				autoplay:3000,
				autoplayDisableOnInteraction:false
			});
			var shopCategoryList = data.shopCategoryList;
			var categoryHTML = '';
			shopCategoryList.map(function(item,index){
				categoryHTML += '<div class="col-50 shop-classify" data-category="'
					+item.shopCategoryId+'">'+'<div class="word">'
					+'<p class="shop-title">'+item.shopCategoryName+'</p>'
					+'<p class="shop-desc">'+item.shopCategoryDesc+'</p>'
					+'</div>'
					+'<div class="shop-classify-img-wrap">'
					+'<img class="shop-img" src="/image'
					+ item.shopCategoryImg + '">'+'</div></div>';
			});
			$(".row").html(categoryHTML);
		}else{
			$.toast("获取信息失败！");
		}
	});
	
	//若点击 “我的”，显示侧边栏
	$("#me").click(function(){
		$.openPanel("#panel-right-demo");
	});
	//点击分类，去该分类的店铺
	$(".row").on('click','.shop-classify',function(e){
		var shopCategoryId=e.currentTarget.dataset.category;
		var newUrl = '/frontend/viewshoplist?parentId='+shopCategoryId;
		window.location.href=newUrl;
	});
	
	
	
});