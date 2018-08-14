package com.bob.o2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bob.o2o.dao.HeadLineMapper;
import com.bob.o2o.entity.HeadLine;
import com.bob.o2o.service.HeadLineService;

/** 
* @author bob 
* @version 创建时间：2018年8月13日 上午10:51:32 
* 类说明 
*/
@Service
public class HeadLineServiceImpl implements HeadLineService{
	
	@Autowired
	private HeadLineMapper headLineMapper;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		// TODO Auto-generated method stub
		return headLineMapper.queryHeadLineList(headLineCondition);
	}

}
