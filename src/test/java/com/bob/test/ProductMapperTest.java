package com.bob.test;

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

import com.bob.o2o.dao.ProductMapper;
import com.bob.o2o.entity.Product;
import com.bob.o2o.entity.ProductCategory;
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
	@Ignore
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
		System.out.println(product.getProductId());
	}
	@Test
	@Ignore
	public void testqueryProductById() {
		Product product = productMapper.queryProductById(27L);
		System.out.println(product);
	}
	@Test
	@Ignore
	public void testUpdateProduct() {
		Product product = new Product();
		product.setProductId(2L);
		product.setProductName("mp4");
		
		Shop shop = new Shop();
		shop.setShopId(44l);
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setproductCategoryId(3L);
		
		product.setShop(shop); 
		product.setProductCategory(productCategory);
		
		int i = productMapper.updateProduct(product);
		System.out.println(i);
		System.out.println(product);
	}
	@Test
	@Ignore
	public void testQueryProductListAndCount() {
		Product product = new Product();
		
		Shop shop = new Shop();
		shop.setShopId(44L);
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setproductCategoryId(4L);
		
		product.setShop(shop);
//		product.setProductCategory(productCategory);
		product.setProductName("map");
		product.setEnableStatus(1);
		List<Product> list = productMapper.queryProductList(product, 0, 100);
		int i = productMapper.queryProductCount(product);
		System.out.println(list.size());
		System.out.println(i);
	}
	@Test
	@Ignore
	public void testupdateProductCategoryToNull() {
		int i = productMapper.updateProductCategoryIdNull(3L);
		System.out.println(i);
	}
}
