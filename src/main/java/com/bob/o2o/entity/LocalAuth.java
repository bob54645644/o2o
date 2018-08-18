package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午4:58:00 
* 类说明 本地账号
* 属性
* localAuthId
* username
* password
* createTime
* lastEditTime
*/

import java.util.Date;

public class LocalAuth {
	//主键ID
	private Long localAuthId;
	//账号
	private String userName;
	//密码
	private String password;
	//创建时间
	private Date createTime;
	//最近一次修改时间
	private Date lastEditTime;
	//个人信息
	private PersonInfo personInfo;
	public Long getLocalAuthId() {
		return localAuthId;
	}
	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	@Override
	public String toString() {
		return "LocalAuth [localAuthId=" + localAuthId + ", userName=" + userName + ", password=" + password
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", personInfo=" + personInfo + "]";
	}
	
}
