package com.bob.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.ProductCategoryMapper;
import com.bob.o2o.dao.ShopCategoryMapper;
import com.bob.o2o.entity.ProductCategory;
import com.bob.o2o.entity.ShopCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月6日 下午12:17:31 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProducCategoryMapperTest {
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	@Test
	@Ignore
	public void testQueryProductCategoryByShopId() {
		List<ProductCategory> list = productCategoryMapper.queryProductCategoryByShopId(44L);
		System.out.println(list.size());
		System.out.println(list);
	}
	@Test
	@Ignore
	public void testInsertPCList() {
		List<ProductCategory> list = new ArrayList<>();
		ProductCategory productCategory1 = new ProductCategory();
		ProductCategory productCategory2 = new ProductCategory();
		ProductCategory productCategory3 = new ProductCategory();
		
		productCategory1.setProductCategoryName("11");
		productCategory2.setProductCategoryName("22");
		productCategory3.setProductCategoryName("33");
		
		productCategory1.setShopId(44L);
		productCategory2.setShopId(44L);
		productCategory3.setShopId(44L);
		
		productCategory1.setCreateTime(new Date());
		productCategory2.setCreateTime(new Date());
		productCategory3.setCreateTime(new Date());
		
		productCategory1.setPriority(4);
		productCategory2.setPriority(4);
		productCategory3.setPriority(4);
		
		list.add(productCategory1);
		list.add(productCategory2);
		list.add(productCategory3);
//		System.out.println(list);
		
		int i = productCategoryMapper.insertProductCategoryList(list);
		System.out.println(i);
	}
	@Test
	public void testDeleteProductCategory() {
		int i = productCategoryMapper.deleteProductCategory(19, 44);
		System.out.println(i);
	}

}
