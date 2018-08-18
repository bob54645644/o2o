package com.bob.o2o.dto;
/** 
* @author bob 
* @version 创建时间：2018年8月16日 下午3:26:54 
* 类说明 
*/

import java.util.List;

import com.bob.o2o.entity.LocalAuth;
import com.bob.o2o.enums.LocalAuthStateEnum;

public class LocalAuthExecution {
	
	private int state;
	private String stateInfo;
	private int count;
	private LocalAuth localAuth;
	private List<LocalAuth> localAuthList;
	public LocalAuthExecution() {
		
	}
	
	//操作失败的构造器
	public LocalAuthExecution(LocalAuthStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器1
	public LocalAuthExecution(LocalAuthStateEnum stateEnum,
			LocalAuth localAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.localAuth = localAuth;
	}
	//成功的构造器2
	public LocalAuthExecution(LocalAuthStateEnum stateEnum,
			List<LocalAuth> localAuthList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.localAuthList = localAuthList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalAuth getLocalAuth() {
		return localAuth;
	}

	public void setLocalAuth(LocalAuth localAuth) {
		this.localAuth = localAuth;
	}

	public List<LocalAuth> getLocalAuthList() {
		return localAuthList;
	}

	public void setLocalAuthList(List<LocalAuth> localAuthList) {
		this.localAuthList = localAuthList;
	}
	

}
