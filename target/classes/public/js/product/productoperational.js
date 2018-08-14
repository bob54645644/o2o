$(function() {
	// 新增产品的url
	var addProductUrl = "/shopadmin/addproduct";
	// 通过shopId查询所有商品类别的url,新增使用
	var queryPCByShopIdUrl = "/shopadmin/getproductcategorybyshopid";

	// 修改产品信息的url
	var modifyProductUrl = "/shopadmin/modifyproduct"

	// 查找productId
	var productId = getQueryString("productId");

	// 通过productId查找产品信息
	var getProductUrl = "/shopadmin/getproductbyid?productId="+productId;

	// 判断是新增还是编辑
	var isEdit = productId ? true : false;

	if (isEdit) {
		// 若为编辑，获得产品信息
		getInfo(productId); 
	} else {
		// 为新增，获得所有产品类别
		getCategory();
	}
	// 编辑触发
	function getInfo(productId) {
		$.getJSON(getProductUrl,function(data){
			if(data.success){
				var product2 = data.product;
				//产品名称
				$("#product-name").val(product2.productName);
				//优先级
				$("#product-priority").val(product2.priority);
				//原价
				$("#product-normalPrice").val(product2.normalPrice);
				//现价
				$("#product-promotion").val(product2.promotionPrice);
				//商品描述
				$("#product-desc").val(product2.productDesc);
				//商品分类，待会完成
				var productCategoryList = data.productCategoryList;
				var tempHtml = '';
				productCategoryList.map(function(item,index){
					tempHtml += '<option data-id="'
						+item.productCategoryId+'">'
						+item.productCategoryName+'</option>';
				});
				$("#product-category").html(tempHtml);
				//选中原来的产品类别
				$('#product-category option[data-id="'
						+product2.productCategory.productCategoryId+'"]')
						.attr("selected","selected");
			}else{
				$.toast(data.errMsg);
			}
		});
	}
	// 新增触发
	function getCategory() {
		$.getJSON(queryPCByShopIdUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.pcList;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-id="' + item.productCategoryId
							+ '">' + item.productCategoryName + '</option>';
				});
				$("#product-category").html(optionHtml);
			}else{
				$.toast(data.errMsg);
			}
		});
	}

	// 点击提交按钮的处理
	$('#submit').click(
			function() {
				// 商品对象
				var product = {};

				product.productName = $("#product-name").val();
				product.productCategory = {
					productCategoryId : $("#product-category").find('option')
							.not(function() {
								return !this.selected;
							}).data('id')
				};
				product.priority = $("#product-priority").val();
				product.normalPrice = $("#product-normalPrice").val();
				product.promotionPrice = $("#product-promotion").val();
				product.productDesc = $("#product-desc").val();
				//这一步很关键,不管有没有productId,都赋值
				product.productId = productId;

				// 缩略图
				var thumbnail = $("#product-thumbnail")[0].files[0];
				var formData = new FormData();
				// 添加商品
				formData.append("productStr", JSON.stringify(product));
				// 添加缩略图
				formData.append("thumbnail", thumbnail);
				// 遍历添加详情图
				$(".product-detail").map(
						function(index, item) {
							if ($(".product-detail")[index].files.length > 0) {
								formData.append('productImg' + index,
										$(".product-detail")[index].files[0]);
							}
						});
				// 验证码
				var verifyCodeActual = $("#j_captcha").val();
				if (verifyCodeActual && verifyCodeActual != '') {
					formData.append('verifyCodeActual', verifyCodeActual);
				} else {
					$.toast('验证码不能为空');
					return;
				}
				var submitUrl = null;
				if(isEdit){
					submitUrl = modifyProductUrl;
				}else{
					submitUrl = addProductUrl;
				}
				// 后台提交数据
				$.ajax({
					url : submitUrl,
					type : 'post',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功');
							$("#captcha_img").click();
						} else {
//							$.toast("提交失败");
							$.toast(data.errMsg);
							$("#captcha_img").click();
						}
					}
				});

			});
	// 上传一张缩略图之后，再加一个上传按钮，最多6个
	$('#product-temp').on(
			'change',
			'.product-detail:last-child',
			function() {
				if ($('.product-detail').length < 7) {
					console.log($('.product-detail').length);
					$("#product-temp").append(
							'<input type="file" class="product-detail">');
				}
			});
});