package com.bob.o2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月5日 上午8:49:20 
* 类说明 
*/

import java.util.List;

import com.bob.o2o.dto.AreaExecution;
import com.bob.o2o.entity.Area;

public interface AreaService {
	List<Area> getAreaList();
	AreaExecution addArea(Area area);
	

}
