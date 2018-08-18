package com.bob.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.o2o.dao.LocalAuthMapper;
import com.bob.o2o.dto.LocalAuthExecution;
import com.bob.o2o.entity.LocalAuth;
import com.bob.o2o.entity.PersonInfo;
import com.bob.o2o.enums.LocalAuthStateEnum;
import com.bob.o2o.exceptions.LocalAuthOperationException;
import com.bob.o2o.service.LocalAuthService;
import com.bob.o2o.utils.MD5;

/** 
* @author bob 
* @version 创建时间：2018年8月16日 下午3:51:20 
* 类说明 
*/
@Service
public class LocalAuthServiceImpl implements LocalAuthService{
	
	@Autowired
	private LocalAuthMapper localAuthMapper;
	
	// 通过账号和密码查询本地账号信息
	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthMapper.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
	}
	
	// 通过userId查询本地账号信息
	@Override
	public LocalAuth getLocalAuthByUserId(long userId) {
		return localAuthMapper.queryLocalByUserId(userId);
	}
	
	// 绑定微信，生成本地账号,//////待完成
	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 新增账户	
	@Override
	@Transactional
	public LocalAuthExecution addLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// TODO Auto-generated method stub
		if(localAuth !=null && localAuth.getUserName() !=null && localAuth.getPassword()!=null) {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(1L);//有待改进
			localAuth.setPersonInfo(personInfo);
			int effectedNum = localAuthMapper.insertLocalAuth(localAuth);
			if(effectedNum<=0) {
				throw new LocalAuthOperationException("新增账户失败");
			}
			return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
		}else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
	
	// 修改登录密码
	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException {
		if(userId !=null && username !=null && password !=null && newPassword !=null) {
			try {
				int effectedNum = localAuthMapper.updateLocalAuth(userId, username, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				//判断更新是否成功
				if(effectedNum <=0) {
					throw new LocalAuthOperationException("更新密码失败");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			}catch(Exception e) {
				throw new LocalAuthOperationException(e.getMessage());
			}
		}else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}

}
