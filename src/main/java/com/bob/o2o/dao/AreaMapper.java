package com.bob.o2o.dao;

import java.util.List;

import com.bob.o2o.entity.Area;

/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午10:51:21 
* 类说明 
*/
public interface AreaMapper {
	List<Area> findAll();
	int insertArea(Area area);
	int deleteArea(int areaId);
	int batchDeleteArea(List<Integer> list);
	int updateArea(Area area);
}
