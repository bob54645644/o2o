package com.bob.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ProductExecution;
import com.bob.o2o.entity.Product;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.service.ProductService;

/** 
* @author bob 
* @version 创建时间：2018年8月9日 上午8:59:06 
* 类说明 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
	public void testaddProduct() throws FileNotFoundException {
		Product product = new Product();
		product.setProductName("8-9-1");
		product.setProductDesc("7777");
		product.setPriority(2);
		Shop shop = new Shop();
		shop.setShopId(44L);
		product.setShop(shop);
		
		File file = new File("e:/ftp.png");
		FileInputStream fileInputStream = new FileInputStream(file);
		ImageHolder imageHolder = new ImageHolder(file.getName(), fileInputStream);
		
		File file1 = new File("e:/ftp.png");
		FileInputStream fileInputStream1 = new FileInputStream(file1);
		ImageHolder imageHolder1 = new ImageHolder(file1.getName(), fileInputStream1);
		List<ImageHolder> list = new ArrayList<>();
		list.add(imageHolder1);
		
		productService.addProduct(product, imageHolder, list);
	}
	@Test
	@Ignore
	public void testUpdateProduct() throws FileNotFoundException {
		Product product = new Product();
		product.setProductId(28L);
		product.setProductName("8-9-1");
		product.setProductDesc("7777");
		product.setPriority(2);
		
		Shop shop = new Shop();
		shop.setShopId(44L);
		product.setShop(shop);
		
		File file = new File("e:/ftp.png");
		FileInputStream fileInputStream = new FileInputStream(file);
		ImageHolder imageHolder = new ImageHolder(file.getName(), fileInputStream);
//		ImageHolder imageHolder = new ImageHolder(null, null);
		
		File file1 = new File("e:/ftp.png");
		FileInputStream fileInputStream1 = new FileInputStream(file1);
		ImageHolder imageHolder1 = new ImageHolder(file1.getName(), fileInputStream1);
		List<ImageHolder> list = new ArrayList<>();
		list.add(imageHolder1);
		
		ProductExecution execution = productService.updateProduct(product, imageHolder, list);
		System.out.println(execution.getStateInfo());
	}
	@Test
	public void testqueryProductById() {
		ProductExecution execution = productService.queryProductByid(29L);
		System.out.println(execution.getStateInfo());
		System.out.println(execution.getProduct());
	}
}
