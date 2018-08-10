package com.bob.o2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月6日 下午12:58:01 
* 类说明 
*/

import java.util.List;

import com.bob.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
