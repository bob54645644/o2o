package com.bob.test;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dto.LocalAuthExecution;
import com.bob.o2o.entity.LocalAuth;
import com.bob.o2o.service.LocalAuthService;

/** 
* @author bob 
* @version 创建时间：2018年8月16日 下午6:13:07 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest {
	
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testAddLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		localAuth.setUserName("波波");
		localAuth.setPassword("123");
		LocalAuthExecution lae = localAuthService.addLocalAuth(localAuth);
		System.out.println(lae.getStateInfo());
	}
	@Test
	@Ignore
	public void testModifyLocalAuth() {
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(2L, "xiu", "123", "123");
		System.out.println(lae.getStateInfo());
	}

}
