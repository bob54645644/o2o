package com.bob.o2o.dao;
/** 
* @author bob 
* @version 创建时间：2018年8月6日 上午11:34:43 
* 类说明 
*/

import java.util.List;

import com.bob.o2o.entity.ShopCategory;

public interface ShopCategoryMapper {
	
	List<ShopCategory> queryShopCategory(ShopCategory shopCategoryCondition);

}
