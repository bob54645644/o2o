package com.bob.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.o2o.entity.Product;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午9:04:20 
* 类说明 
*/
public interface ProductMapper {
	//新增商品
	int insertProduct(Product product);
	//根据productId查询商品
	Product queryProductById(long ProductId);
	
	
	//修改商品
	int updateProduct(Product product);
	
	//删除商品类别时，将该商品类别下的商品的商品类别置为空
	int updateProductCategoryIdNull(long productCategoryId);
	
	//通过product的信息分页查询
	List<Product> queryProductList(@Param("productCondition")Product productCondition,
			@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	//通过同样的条件查询商品总数
	int queryProductCount(@Param("productCondition")Product productCondition);
	
	
}
