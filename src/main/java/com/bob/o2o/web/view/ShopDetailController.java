package com.bob.o2o.web.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.o2o.dto.ProductExecution;
import com.bob.o2o.entity.Product;
import com.bob.o2o.entity.ProductCategory;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.service.ProductCategoryService;
import com.bob.o2o.service.ProductService;
import com.bob.o2o.service.ShopService;
import com.bob.o2o.utils.HttpServletRequestUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月15日 上午10:08:19 
* 类说明 
*/

@RestController
@RequestMapping("/view")
public class ShopDetailController {
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	//获取店铺信息以及该店铺的商品类别列表
	@GetMapping("/shopdetailinfo")
	public Map<String, Object> shopDetailInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		
		Shop shop = null;
		List<ProductCategory> productCategoryList = null;
		
		if(shopId !=-1L) {
			//有shopId，获取店铺信息
			shop = shopService.getByShopId(shopId);
			productCategoryList = productCategoryService.getProductCategoryByShopId(shopId);
			modelMap.put("success", true);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "emp shopId");
		}
		return modelMap;
	}
	
	//根据查询条件，分页列出该店铺下的所有商品
	@GetMapping("/productlist")
	public Map<String,Object> shopDetailList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		//空值判断
		if((pageIndex >-1)&&(pageSize>-1)&&(shopId>-1)) {
			//尝试获得productCategoryId
			Long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			//尝试获得productName
			String productName = HttpServletRequestUtil.getString(request, "productName");
			//组合查询条件
			Product productCondition = compactProductCondition(shopId,productCategoryId,productName);
			//按照组合条件查询
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("success", true);
			modelMap.put("count", pe.getCount());
			modelMap.put("productList", pe.getProductList());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shopId or pageIndex or pageSize为空");
		}
		return modelMap;
	}

	private Product compactProductCondition(Long shopId, Long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		//若productCategoryId不为空，赋值
		if(productCategoryId != -1L) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setproductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		//如果有productName
		if(productName !=null) {
			productCondition.setProductName(productName);
		}
		//只允许状态为1的商品显示
		productCondition.setEnableStatus(1);
		return productCondition;
	}
}
