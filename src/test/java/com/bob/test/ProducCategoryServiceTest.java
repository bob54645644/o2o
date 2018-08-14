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
import com.bob.o2o.dto.ProductCategoryExecution;
import com.bob.o2o.entity.ProductCategory;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.service.ProductCategoryService;

/** 
* @author bob 
* @version 创建时间：2018年8月6日 下午12:17:31 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProducCategoryServiceTest {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Test
	@Ignore
	public void testInsertPCList() {
		List<ProductCategory> list = new ArrayList<>();
		ProductCategory productCategory1 = new ProductCategory();
		ProductCategory productCategory2 = new ProductCategory();
		
		productCategory1.setProductCategoryName("11");
		productCategory2.setProductCategoryName("22");
		
		productCategory1.setShopId(44L);
		productCategory2.setShopId(44L);
		
		productCategory1.setCreateTime(new Date());
		productCategory2.setCreateTime(new Date());
		
		productCategory1.setPriority(4);
		productCategory2.setPriority(4);
		
		list.add(productCategory1);
		list.add(productCategory2);
//		System.out.println(list);
		
		ProductCategory productCategory = new ProductCategory();
		List<ProductCategory> list2 = new ArrayList<>();
//		list2.add(productCategory);
		ProductCategoryExecution batchProductCategoryList = productCategoryService.batchProductCategoryList(list);
		System.out.println(batchProductCategoryList.getStateInfo());
	}
	@Test
	@Ignore
	public void testdeleteproductCategory() {
		ProductCategoryExecution execution = productCategoryService.removeProductCategory(3L,44L);
		System.out.println(execution.getStateInfo());
	}
	
}
