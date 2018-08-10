package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午6:24:39 
* 类说明  头条实体类
* 属性
* lineId
* lineName
* lineLink
* lineImg
* priority
* enableStatus
* createTime
* lastEditTime
*/

import java.util.Date;

public class HeadLine {
	//主键ID
	private Long lineId;
	//头条名字
	private String lineName;
	//头条连接
	private String lineLink;
	//头条图片
	private String lineImg;
	//权重
	private Integer priority;
	//头条状态 0、不可用 1、可用
	private Integer enableStatus;
	//创建时间
	private Date createTime;
	//最近一次更改时间
	private Date lastEditTime;
	public Long getLineId() {
		return lineId;
	}
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineLink() {
		return lineLink;
	}
	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}
	public String getLineImg() {
		return lineImg;
	}
	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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
}
