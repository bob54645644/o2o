/*
 * 1、获取店铺列表，填充页面
 * 2、
 */
$(function(){
	getShopList();
	function getShopList(){
		$.ajax({
			url: "/shopadmin/getshoplist",
			type: "get",
			dataType:"json",
			success: function(data){
				if(data.success){
					//处理shopList
					handleList(data.shopList);
					//处理user
					handleUser(data.user);
				}
			}
		});
	}
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html += '<div class="row row-shop">'
				+'<div class="col-40">'
				+item.shopName+'</div><div class="col-40">'
				+shopStatus(item.shopStatus)+'</div><div class="col-20">'
				+goShop(item.shopStatus,item.shopId)+'</div></div>';
		});
		//填充信息
		$(".shop-wrap").html(html);
	}
	function handleUser(data){
		var userName = data.name;
		$("#user-name").text(userName);
	}
	//实现HandleList里面的两个函数
	function shopStatus(status){
		if(status == 0){
			return "审核中";
		}else if(status == -1){
			return "店铺非法";
		}else if(status == 1){
			return "审核通过";
		}
	}
	function goShop(status,shopId){
		if(status == 1){
			return '<a href="/frontend/shopmanagement?shopId='+shopId+'">进入</a>';
		}else{
			return '';
		}
	}
});