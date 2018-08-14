package com.bob.o2o.service;


import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ShopExecution;
import com.bob.o2o.entity.Shop;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午8:15:43 
* 类说明 
*/
public interface ShopService {
	ShopExecution addShop(Shop shop,ImageHolder imageHolder);
	ShopExecution modifyShop(Shop shop,ImageHolder imageHolder);
	Shop getByShopId(Long shopId);
	
	//根据shopCondition分页返回相应的店铺列表
	ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
