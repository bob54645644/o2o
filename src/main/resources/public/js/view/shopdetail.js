$(function(){
	// 加载标志符
	var loading = false;
	// 分页允许返回的最大条数
	var maxItems = 999;
	// 一页返回的最大条数
	var pageSize =3;
	// 获取店铺列表的url
	var listUrl = '/view/productlist';
	// 获取店铺类别列表以及区域列表的url
	var searchDivUrl = '/view/shopdetailinfo';
	// 页码
	var pageNum = 1;
	// 当前url中获取parentId
	var shopId = getQueryString('shopId');
	
	var productCategoryId="";
	var productName = '';
	// 初始化店铺类别和区域列表
	getSearchDivData();
	// 预先加载3条店铺信息
	addItems(pageNum,pageSize);
	
	function getSearchDivData(){
		var url = searchDivUrl + '?shopId='+shopId;
		$.getJSON(url,function(data){
			if(data.success){
				// 获取店铺信息，并填充
			var shop = data.shop;
			$("#shop-cover-pic").attr('src','/image'+shop.shopImg);
			$("#shop-update-time").html(
					new Date(shop.lastEditTime).Format("yyyy-MM-dd"));
			$("#shop-name").html(shop.shopName);
			$("#shop-desc").html(shop.shopDesc);
			$("#shop-addr").html(shop.shopAddr);
			$("#shop-phone").html(shop.phone);
			// 获取后台返回的该店铺的商品类别列表
			var productCategoryList = data.productCategoryList;
			var html = '';
			// 遍历商品类别列表，生成a标签
			productCategoryList.map(function(item,index){
				html += '<a href="#" class="button" data-product-search-id="'
					+item.productCategoryId+'">'
					+item.productCategoryName+'</a>';
			});
			$("#shopdetail-button-div").html(html);
			
			}else{
				$.toast("信息获取失败！");
			}
		});
	}
	function addItems(pageIndex,pageSize){
		//拼接出url
		var url = listUrl +'?pageIndex='+pageIndex
			+ '&pageSize='+pageSize
			+'&productCategoryId='+productCategoryId
			+'&productName='+productName
			+'&shopId='+shopId;
		//置位标志符
		loading=true;
		$.getJSON(url,function(data){
			if(data.success){
				maxItems = data.count;
				var html = '';
				data.productList.map(function(item,index){
					html += '<div class="card" data-product-id="'
						+item.productId +'">'
						+'<div class="card-header">'
						+item.productName + '</div>'
						+'<div class="card-content">'
						+'<div class="list-block media-list">'
						+'<ul>'
						+'<li class="item-content">'
						+'<div class="item-media">'
						+'<img src="/image'+item.imgAddr+'" width="44"></div>'
						+'<div class="item-inner">'
						+'<div class="item-subtitle"'+item.productDesc+'</div>'
						+'</div></li></ul></div></div>'
						+'<div class="card-footer">'
						+'<p class="color-gray">'
						+new Date(item.lastEditTime).Format("yyyy-MM-dd")
						+'更新</p>'
						+'<span>点击查看</span>'
						+'</div><div>';
				});
				$(".list-div").append(html);
				//统计卡片总数
				var total = $('.list-div .card').length;
				if(total>=maxItems){
					//隐藏提示符
					$('.infinite-scroll-preloader').hide();
				}else{
					//显示提示符
					$('.infinite-scroll-preloader').show();
				}
				//页码加一
				pageNum +=1;
				//加载标志符置为否
				loading =false;
				//刷新页面
				$.refreshScroller();
			}else{
				$.toast("商品列表获取失败！");
			}
		});
	}
	
	//屏幕下滑，自动分页搜索
	$(document).on('infinite','.infinite-scroll-bottom',function(){
		if(loading){
			return ;
		}else{
			addItems(pageNum,pageSize);
		}
	});
	//选择新的类别之后，重置页码，清空原来的商品列表，按照新条件查询
	$('#shopdetail-button-div').on('click','.button',function(e){
		//获取商品类别id
		productCategoryId = e.target.dataset.productSearchId;
		if(productCategoryId){
			if($(e.target).hasClass('button-fill')){
				$(e.target).removeClass('button-fill');
				productCategoryId = '';
			}else{
				$(e.target).addClass('button-fill').siblings()
					.removeClass('button-fill');
			}
			$('.list-div').empty();
			pageNum=1;
			addItems(pageNum,pageSize);
		}
	});
	
	//点击商品的卡片进入该商品的详情页
	$('.list-div').on('click','.card',function(e){
		var productId = e.currentTarget.dataset.productId;
		window.location.href='/frontend/productdetail?productId='+productId;
	});
	
	//根据商品名称，模糊查询
	$('#search').on('change',function(e){
		productName = e.target.value;
		$('.list-div').empty();
		pageNum =1;
		addItems(pageNum,pageSize);
	});
	
	$.init();
});

	
	