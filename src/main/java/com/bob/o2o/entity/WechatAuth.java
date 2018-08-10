package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午4:54:30 
* 类说明 微信登录实体类
* 参数
* wechatAuthId
* openId
* createTime
* personInfo
*/

import java.util.Date;

public class WechatAuth {
	
	//主键ID
	private Long weChatAuthId;
	//用户的微信信息凭证，唯一
	private String openId;
	//创建时间
	private Date createTime;
	//用户信息
	private PersonInfo personInfo;
	public Long getWeChatAuthId() {
		return weChatAuthId;
	}
	public void setWeChatAuthId(Long weChatAuthId) {
		this.weChatAuthId = weChatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

}
