package com.bob.o2o.dao;
/** 
* @author bob 
* @version 创建时间：2018年8月13日 上午10:09:07 
* 类说明 
*/

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.o2o.entity.HeadLine;

public interface HeadLineMapper {
	List<HeadLine> queryHeadLineList(@Param("headLineCondition")HeadLine headLine);
}
