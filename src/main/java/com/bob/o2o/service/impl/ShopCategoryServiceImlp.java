package com.bob.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bob.o2o.dao.ShopCategoryMapper;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.service.ShopCategoryService;

/** 
* @author bob 
* @version 创建时间：2018年8月6日 下午12:59:38 
* 类说明 
*/
@Service
public class ShopCategoryServiceImlp implements ShopCategoryService{
	
	@Autowired
	private ShopCategoryMapper shopCategoryMapper;
	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
		// TODO Auto-generated method stub
		return shopCategoryMapper.queryShopCategory(shopCategory);
	}

}
