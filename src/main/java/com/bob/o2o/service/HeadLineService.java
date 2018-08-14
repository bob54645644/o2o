package com.bob.o2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月13日 上午10:49:49 
* 类说明 
*/

import java.io.IOException;
import java.util.List;

import com.bob.o2o.entity.HeadLine;

public interface HeadLineService {
	//查询头条信息
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;

}
