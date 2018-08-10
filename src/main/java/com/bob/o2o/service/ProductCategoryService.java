package com.bob.o2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月8日 上午9:58:28 
* 类说明 
*/

import java.util.List;

import com.bob.o2o.dto.ProductCategoryExecution;
import com.bob.o2o.entity.ProductCategory;

public interface ProductCategoryService {
	
	//根据shopId获取店铺中的商品分类
	List<ProductCategory> getProductCategoryByShopId(Long shopId);
	//批量添加商品类别
	ProductCategoryExecution batchProductCategoryList(List<ProductCategory> productCategories);
	//通过productCategoryId和shopId删除productCategory
	ProductCategoryExecution removeProductCategory(long productCategory,long shopId);
}
