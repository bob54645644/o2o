package com.bob.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.HeadLineMapper;
import com.bob.o2o.entity.HeadLine;

/** 
* @author bob 
* @version 创建时间：2018年8月13日 上午10:25:22 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadLineMapperTest {
	@Autowired
	private HeadLineMapper headLineMapper;
	
	@Test
	public void testQueryHeadLine() {
		List<HeadLine> list = headLineMapper.queryHeadLineList(new HeadLine());
		System.out.println(list.size());
		System.out.println(list);
	}
}
