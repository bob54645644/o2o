package com.bob.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.ShopCategoryMapper;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.service.ShopCategoryService;

/** 
* @author bob 
* @version 创建时间：2018年8月6日 下午12:17:31 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopCategoryServiceTest {
	@Autowired
	private ShopCategoryService categoryService;
	@Test
	public void TestAgetShopCategoryList(){
		ShopCategory shopCategory = new ShopCategory();
		List<ShopCategory> list = categoryService.getShopCategoryList(shopCategory);
		System.out.println(list.size());
		
		ShopCategory shopCategory2 = new ShopCategory();
		shopCategory2.setShopCategoryId(1L);
		
		ShopCategory shopCategory3 = new ShopCategory();
		shopCategory3.setParent(shopCategory2);
		
		List<ShopCategory> list2 = categoryService.getShopCategoryList(shopCategory3);
		System.out.println(list2.size());
		System.out.println(list2);
	}

}
