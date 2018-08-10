package com.bob.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.ProductImgMapper;
import com.bob.o2o.entity.ProductImg;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午10:13:49 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgMapperTest {
	
	@Autowired
	private ProductImgMapper imgMapper;
	
	@Test
	public void testbatchInsertImg() {
		ProductImg productImg1 = new ProductImg();
		ProductImg productImg2 = new ProductImg();

		productImg1.setImgAddr("test1");
		productImg2.setImgAddr("test2");
		
		productImg1.setProductId(1L);
		productImg2.setProductId(1L);
		
		List<ProductImg> list = new ArrayList<>();
		list.add(productImg1);
		list.add(productImg2);
		
		int i = imgMapper.batchInsertProductImg(list);
		System.out.println(i);
	}

}
