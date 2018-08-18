package com.bob.test;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.LocalAuthMapper;
import com.bob.o2o.entity.LocalAuth;
import com.bob.o2o.entity.PersonInfo;

/** 
* @author bob 
* @version 创建时间：2018年8月16日 上午11:12:07 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthMapperTest {
	
	@Autowired
	private LocalAuthMapper localAuthMapper;
	//测试插入本地账号
	@Test
	@Ignore
	public void testInsertLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		localAuth.setUserName("xiu");
		localAuth.setPassword("123");
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(2L);
		localAuth.setPersonInfo(personInfo);
		
		int i = localAuthMapper.insertLocalAuth(localAuth);
		System.out.println(i);
		System.out.println(localAuth);
		
	}
	
	@Test
	@Ignore
	public void testupdate() {
		int i = localAuthMapper.updateLocalAuth(2L, "xiu", "123", "123", new Date());
		System.out.println(i);
	}
	@Test
	@Ignore
	public void testQueryByUserId() {
		LocalAuth auth = localAuthMapper.queryLocalByUserId(1);
		System.out.println(auth);
	}
	@Test
	public void testQueryByUsernameAndPassword() {
		LocalAuth auth = localAuthMapper.queryLocalByUserNameAndPwd("bob", "123");
		System.out.println(auth);
	}
}
