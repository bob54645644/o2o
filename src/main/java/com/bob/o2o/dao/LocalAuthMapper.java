package com.bob.o2o.dao;
/** 
* @author bob 
* @version 创建时间：2018年8月16日 上午9:29:18 
* 类说明 
*/

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.bob.o2o.entity.LocalAuth;

public interface LocalAuthMapper {
	//通过账号和密码查询账号信息，登录时使用
	LocalAuth queryLocalByUserNameAndPwd(@Param("username")String username,
			@Param("password")String password);
	//通过userId查询对应的账号信息
	LocalAuth queryLocalByUserId(@Param("userId")long userId);
	//添加账号
	int insertLocalAuth(LocalAuth localAuth);
	//通过userId，userName，password更改密码
	int updateLocalAuth(@Param("userId")long userId,@Param("username")String username
			,@Param("password")String password,@Param("newPassword")String newPassword,
			@Param("lastEditTime")Date lastEditTime);
	
}
