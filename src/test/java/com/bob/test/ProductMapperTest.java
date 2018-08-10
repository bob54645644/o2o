package com.bob.test;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.ProductMapper;
import com.bob.o2o.entity.Product;
import com.bob.o2o.entity.Shop;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午9:38:42 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;
	@Test
	public void testInsertProduct() {
		Product product = new Product();
		product.setProductName("map3");
		product.setEnableStatus(1);
		product.setCreateTime(new Date());
		product.setPriority(1);
		
		Shop shop = new Shop();
		shop.setShopId(44L);
		
		product.setShop(shop);
		product.setProductDesc("好听");
		
		int i = productMapper.insertProduct(product);
		System.out.println(i);
		System.out.println(product.getProductID());
	}
}
