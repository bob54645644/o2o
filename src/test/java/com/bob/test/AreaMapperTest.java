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

import com.bob.o2o.dao.AreaMapper;
import com.bob.o2o.entity.Area;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 上午7:13:10 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AreaMapperTest {
	@Autowired
	private AreaMapper areaMapper;
	@Test
//	@Ignore//查询所有
	public void testFFindAll() {
		List<Area> list = areaMapper.findAll();
		list.forEach(x->System.out.println(x));
	}
	@Test
	@Ignore//增加
	public void testAInsert() {
		Area area = new Area();
		area.setAreaName("这8");
		area.setPriority(3);
//		area.setCreateTime(new Date());
//		area.setLastEditTime(new Date());
		int i= areaMapper.insertArea(area);
		System.out.println("插入的条数："+i);
	}
	@Test//更改
	public void testBUpdate() {
		Area area = new Area();
		area.setAreaId(2);
		area.setAreaName("a");
		area.setPriority(4);
		area.setLastEditTime(new Date());
		
		int updateArea = areaMapper.updateArea(area);
		System.out.println("修改的条数："+updateArea);
	}
	@Test
	@Ignore//删除一条
	public void testDelete() {
		int i = areaMapper.deleteArea(22);
		System.out.println("删除的条数:"+i);
	}
	@Test
	@Ignore//批量删除
	public void testEbatchDelete() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(9);
		list.add(10);
		list.add(11);
		
		int i = areaMapper.batchDeleteArea(list);
		System.out.println("删除的条数："+i);
	}
	
	

}
