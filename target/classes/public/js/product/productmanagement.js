//1、获取所有商品信息
//2、填充 待填充区域
//3、处理编辑操作
//4、处理上下架操作
//5、处理预览操作
$(function(){
	//根据shopId获取产品列表的url
	var getProductListUrl = "/shopadmin/getproductlistbyshopId?pageIndex=0&pageSize=100";
	
	//编辑产品信息页面的url
	var editProductUrl = "/frontend/productoperational?productId=";
	//下架的url
	var statusChangeUrl = "/shopadmin/modifyproduct?statusChange=true";
	//预览的url，暂时没有
	
	//获取信息
	initFun();
	function initFun(){
		$.getJSON(getProductListUrl,function(data){
			if(data.success){
				var tempHtml = '';
				data.productList.map(function(item,index){
					tempHtml +=  '<div class="row row-product">'
						+'<div class="col-40">'
						+item.productName+'</div><div class="col-40">'
						+item.priority+'</div><div class="col-20">'
						+'<a href="'+editProductUrl+item.productId+'">编辑</a>'
						+statusChange(item.enableStatus,item.productId)
						+'<a href="'+'#'+'">预览</a>'
						+'</div></div>';
				});
				$(".product-wrap").html(tempHtml);
			}else{
				$.toast(data.errMsg);
			}
		});
	}
	function statusChange(status,productId){
		if(status ==1){
			return '<a class="edit" data-id="'+productId
			+'" data-status="'+status+'"'
			+' href="'+'#'+'">下架</a>';
		}else{
			return '<a class="edit" data-id="'+productId
			+'" data-status="'+status+'"'
			+' href="'+'#'+'">上架</a>';
		}
	}
	//处理上下架
	$(".product-wrap").on('click','a',function(e){
		var statusss=target.dataset.status;
		var product = {};
		if(e.currentTarget.dataset.status ==1){
			product.enableStatus=0;
		}else{
			product.enableStatus=1;
		}
		product.productId=e.currentTarget.dataset.id;
		$.confirm('确定吗？',function(){
			$.ajax({
				url:statusChangeUrl,
				type:'post',
				data:{
					productStr:JSON.stringify(product)
				},
				dataType:'json',
				success:function(data){
					if(data.success){
						$.toast("操作成功！");
						initFun();
					}else{
						$.toast("操作失败！");
					}
				}
			});
		});
	});
});
