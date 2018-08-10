package com.bob.o2o.dao;

import com.bob.o2o.entity.Product;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午9:04:20 
* 类说明 
*/
public interface ProductMapper {
	//新增商品
	int insertProduct(Product product);
}
