package com.bob.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.o2o.entity.Shop;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午3:21:35 
* 类说明 
*/
public interface ShopMapper {
	
	//分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类型，区域Id，owrne
	//@Param shopCondition
	//@param rowIndex 从第几行取数据
	//@param pageSize返回的条数
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition , 
			@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	
	//查询店铺总数
	int queryShopCount(@Param("shopCondition")Shop shopCondition );
	
	//新增店铺
	int insertShop(Shop shop);
	//更新店铺
	int updateShop(Shop shop);
	//根据shopId查询shop
	Shop queryByShopId(Long shopId);
}
