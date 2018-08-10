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

import com.bob.o2o.dto.AreaExecution;
import com.bob.o2o.entity.Area;
import com.bob.o2o.service.AreaService;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 上午8:56:30 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AreaServiceTest {
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetList() {
		List<Area> list = areaService.getAreaList();
		list.forEach(x->System.out.println(x));
	}
	@Test
	public void testAdd() {
		Area area = new Area();
		area.setAreaName("b3");
		area.setPriority(4);
		area.setCreateTime(new Date());
		
		AreaExecution area2 = areaService.addArea(area);
		System.out.println(area2.getStateInfo());
	}

}
