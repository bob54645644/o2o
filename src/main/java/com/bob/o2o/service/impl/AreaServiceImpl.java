package com.bob.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.o2o.dao.AreaMapper;
import com.bob.o2o.dto.AreaExecution;
import com.bob.o2o.entity.Area;
import com.bob.o2o.enums.AreaStateEnum;
import com.bob.o2o.exceptions.AreaOperationException;
import com.bob.o2o.service.AreaService;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 上午8:55:11 
* 类说明 
*/
@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return areaMapper.findAll();
	}
	
	@Transactional
	@Override
	public AreaExecution addArea(Area area) {
		// TODO Auto-generated method stub
		if(area.getAreaName()!=null&& !"".equals(area.getAreaName())) {
			area.setCreateTime(new Date());
			area.setLastEditTime(new Date());
			try {
				int effectedNum = areaMapper.insertArea(area);
				if(effectedNum>0) {
					return new AreaExecution(AreaStateEnum.SUCCESS,area);
				}else {
					return new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			}catch(Exception e){
				throw new AreaOperationException("添加区域信息失败："+e.toString());
			}
			
		}else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}
	

}
