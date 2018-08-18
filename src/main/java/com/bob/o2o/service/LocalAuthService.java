package com.bob.o2o.service;

import com.bob.o2o.dto.LocalAuthExecution;
import com.bob.o2o.entity.LocalAuth;
import com.bob.o2o.exceptions.LocalAuthOperationException;

/**
 * @author bob
 * @version 创建时间：2018年8月16日 下午3:07:51 类说明
 */
public interface LocalAuthService {
	// 通过账号和密码查询本地账号信息
	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	// 通过userId查询本地账号信息
	LocalAuth getLocalAuthByUserId(long userId);

	// 绑定微信，生成本地账号
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	// 新增账户
	LocalAuthExecution addLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	// 修改登录密码
	LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException;
}
