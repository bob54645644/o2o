$(function(){
	//从url中获取shopId
	var shopId=getQueryString("shopId");
	//有shopId是编辑，没有是新增
	var isEdit = shopId?true:false;
	//新增店铺的url
	var registerShopUrl = "/shopadmin/registershop";
	//编辑店铺的url
	var editShopUrl = "/shopadmin/modifyshop";
	//通过shopId获取店铺信息
	var shopInfoUrl = "/shopadmin/getshopbyid?shopId=" + shopId;
	//新增店铺时，获取店铺类别姐区域信息的初始化url
	var initUrl = "/shopadmin/getshopinitinfo";
	
	if(!isEdit){
		getShopInitInfo();
	}else{
		getShopInfo(shopId);
	}
	//通过shopId获取店铺信息
	function getShopInfo(shopId){
		$.getJSON(shopInfoUrl,function(data){
			if(data.success){
				//若访问成功，从后台的json信息填充shop表单
				var shop = data.shop;
				$("#shop-name").val(shop.shopName);
				$("#shop-addr").val(shop.shopAddr);
				$("#shop-phone").val(shop.phone);
				$("#shop-desc").val(shop.shopDesc);
				//选定店铺类别
				var shopCategory = '<option data-id="'
					+shop.shopCategory.shopCategoryId +'"selected>'
					+shop.shopCategory.shopCategoryName+'</option>';
				//填充区域信息
				var tempAreaHtml = '';
				data.areaList.map(function(item,index){
					tempAreaHtml += '<option data-id="'
						+item.areaId+'">'
						+item.areaName+'</option>';
				});
				$("#area").html(tempAreaHtml);
				//锁定店铺类别 
				$("#shop-category").html(shopCategory);
				$("#shop-category").attr('disabled','disabled');
				//选定所属区域
				$("#area option[data-id='"+shop.area.areaId+"']").attr(
						"selected","selected");
			}
		});
	}
	//注册时，获得初始化店铺信息,包括二级店铺类别和区域信息，并赋值
	function getShopInitInfo(){
		$.getJSON(initUrl,function(data){
			if(data.success){
				var tempHtml = "";
				var tempAreaHtml = "";
				data.shopCategoryList.map(function(item,index){
					tempHtml += '<option data-id="'+item.shopCategoryId
					+'">'+item.shopCategoryName+'</option>';
				});
				data.areaList.map(function(item,index){
					tempAreaHtml += '<option data-id="' + item.areaId +'">'
					+item.areaName +'</option>';
				});
				$("#shop-category").html(tempHtml);
				$("#area").html(tempAreaHtml);
			}
		});
	}
	
	$("#submit").click(function(){
		//创建shop对象
		var shop = {};
		if(isEdit){
			//是编辑模式，给shopId赋值
			shop.shopId=shopId;
		}
		//获取表单中的数据填充对应的shop属性中
		shop.shopName = $("#shop-name").val();
		shop.shopAddr = $("#shop-addr").val();
		shop.phone = $("#shop-phone").val();
		shop.shopDesc = $("#shop-desc").val();
		//获取选择的店铺类型
		shop.shopCategory = {
				shopCategoryId: $("#shop-category").find("option").not(function(){
					return !this.selected;
				}).data("id")
		};
		//获取选择的区域
		shop.area = {
				areaId : $("#area").find("option").not(function(){
					return !this.selected;
				}).data("id")
		};
		//获取上传的文件流
		var shopImg = $("#shop-img")[0].files[0];
		
		//生成表单对象，用于传递给后台
		var formData = new FormData();
		//添加图片流
		formData.append("shopImg",shopImg);
		//将json化的shop对象添加到formData中
		formData.append("shopStr",JSON.stringify(shop));
		//获取表单中的验证码
		var verifyCodeActual = $("#j_captcha").val();
		if(!verifyCodeActual){
			$.toast("请输入验证码！");
			return ;
		}
		//$.toast("test");
		formData.append("verifyCodeActual",verifyCodeActual);
		//将数据异步提交至后台
		$.ajax({
			url:(isEdit ? editShopUrl : registerShopUrl),
			type: 'POST',
			data: formData,
			contentType: false,
			processData: false,
			cache : false,
			success : function(data){
				if(data.success){
					$.toast("提交成功！");
					if(!isEdit){
						//若为新增操作，成功后返回到店铺列表
						//window.lication.href="/shopadmin/shoplist";
					}
				}else {
					$.toast("提交失败！"+data.errMsg);
				}
				//更换验证码
				$("#captcha_img").click();
			}
		});
	});
})