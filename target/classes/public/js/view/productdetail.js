$(function(){
	//获得productId
	var productId = getQueryString('productId');
	//获得商品信息的url
	var url = '/view/productdetailinfo?productId='+productId;
	if(productId){
		$.getJSON(url,function(data){
			if(data.success){
				var product = data.product;
				var productImgList = product.productImgList;
				$('#product-img').attr('src','/image'+product.imgAddr);
				$('#product-time').text(new Date(product.lastEditTime).Format("yyyy-MM-dd"));
				$('#product-desc').text(product.productDesc);
				//处理价格
				if(product.normalPrice !=undefined && product.promotionPrice !=undefined){
					$('#price').show();
					$('#normalPrice').html(
							'<del>￥'+product.normalPrice+'</del>'
					);
					$('#promotionPrice').text('￥'+product.promotionPrice);
				}else if(product.normalPrice !=undefined && product.promotionPrice ==undefined){
					$('#price').show();
					$('#promotionPrice').text("￥"+product.normalPrice);
				}else if(product.normalPrice ==undefined && product.promotionPrice !=undefined){
					$('#promotionPrice').text("￥"+product.promotionPrice);
				}
				var imgListHtml = '';
				productImgList.map(function(item,index){
					imgListHtml += '<div><img src="/image'+item.imgAddr + '" width="100%" /></div>';
				});
				$('#imgList').html(imgListHtml);
			}else{
				$.toast(data.errMsg);
			}
		});
	}else{
		$.toast("empty productId!");
	}
	
	//点击打开侧边栏
	$('#me').click(function(){
		$.openPanel('#panel-right-demo');
	});
});