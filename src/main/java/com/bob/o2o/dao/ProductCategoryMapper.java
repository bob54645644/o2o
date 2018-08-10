package com.bob.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.o2o.entity.ProductCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 上午9:37:10 
* 类说明 
*/
public interface  ProductCategoryMapper {
//	 通过shopId查询商品类别 
	List<ProductCategory> queryProductCategoryByShopId(Long shopId);
	//批量添加商品类别
	int insertProductCategoryList(List<ProductCategory> productCategories);
	//通过productCategoryId和shopId删除productCategory
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
			@Param("shopId")long shopId);
}
