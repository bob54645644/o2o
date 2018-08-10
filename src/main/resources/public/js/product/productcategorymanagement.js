$(function() {
	// 根据shopId获得商品类别地址
	var pcUrl = "/shopadmin/getproductcategorybyshopid";
	// 批量添加商品类别地址
	var addUrl = "/shopadmin/addpclist";
	// 删除商品类别的地址
	var removeUrl = "/shopadmin/removePC";

	getList();
	function getList() {
		$.getJSON(pcUrl, function(data) {
			if (data.success) {
				tempHtml = '';
				data.pcList.map(function(item, index) {
					tempHtml += '<div class="row row-product-category now">'
							+ '<div class="col-33 product-category-name">'
							+ item.productCategoryName
							+ '</div><div class="col-33">' + item.priority
							+ '</div><div class="col-33">'
							+ '<a href="#" class="button delete" data-id="'
							+ item.productCategoryId + '">删除</a></div></div>';
				});
				// 必须是html函数才行
				$(".pc-wrap").html(tempHtml);
			} else {
				$.toast(data.errMsg);
			}
		});
	}
	$("#new")
			.click(
					function() {
						newHtml = '';

						newHtml += '<div class="row row-product-category temp"><div class="col-33">'
								+ '<input class="category-input category" type="text"'
								+ 'placeholder="分类名"'
								+ ' />'
								+ '</div><div class="col-33">'
								+ '<input class="category-input priority" type="text" '
								+ 'placeholder="权重"'
								+ '/>'
								+ '</div><div class="col-33">'
								+ '<a href="#" class="button delete"'
								+ '>删除</a></div></div>';
						$(".pc-wrap").append(newHtml);

					});
	$("#submit").click(function() {
		var tempArr = $(".temp");
		var PCList = [];
		tempArr.map(function(index, item) {
			var tempObj = {};
			tempObj.productCategoryName = $(item).find('.category').val();
			tempObj.priority = $(item).find('.priority').val();
			if (tempObj.productCategoryName && tempObj.priority) {
				PCList.push(tempObj);
			}
		});
		$.ajax({
			url : addUrl,
			type : "POST",
			data : JSON.stringify(PCList),
			contentType : "application/json",
			success : function(data) {
				if (data.success) {
					$.toast("提交成功！");
					// 提交成功后，重新加载数据
					getList();
				} else {
					$.toast("提交失败！");
				}
			}
		});
	});
	// 删除新增的,也就是temp临时的
	$(".pc-wrap").on("click", ".row-product-category.temp .delete",
			function(e) {
				console.log($(this).parent().parent());
				$(this).parent().parent().remove();
			});

	// 删除之前的
	$(".pc-wrap").on("click", '.row-product-category.now .delete', function(e) {
		var target = e.currentTarget;
		$.confirm("确定吗？", function() {
			$.ajax({
				url : removeUrl,
				type : "post",
				data : {
					productCategoryId : target.dataset.id
				},
				success : function(data) {
					if (data.success) {
						$.toast('删除成功！');
						getList();
					} else {
						$.toast("删除失败！");
					}
				}
			});
		});
	});
});