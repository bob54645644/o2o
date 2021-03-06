package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午6:53:47 
* 类说明 店铺实体类
* 属性
* shopId
* shopName
* shopDesc
* shopAddr
* phone
* shopImg
* priority
* createTime
* lastEditTime
* enableStatus
* advice
* Area area
* PersonInfo owner
* ShopCategory shopcategory
*/

import java.util.Date;

public class Shop {
	//主键ID
	private Long shopId;
	//店铺名称
	private String shopName;
	//店铺描述
	private String shopDesc;
	//店铺地址
	private String shopAddr;
	//店铺电话
	private String phone;
	//店铺门面图片地址
	private String shopImg;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//最近一次修改时间
	private Date lastEditTime;
	//店铺状态 -1、不可用 0、审核中 1、可用
	private Integer shopStatus;
	//超级管理员给店家的建议
	private String advice;
	//店铺所属区域
	private Area area;
	//店铺的所有者
	private PersonInfo owner;
	//店铺所属类别
	private ShopCategory shopCategory;
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	public Integer getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public PersonInfo getOwner() {
		return owner;
	}
	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopDesc=" + shopDesc + ", shopAddr=" + shopAddr
				+ ", phone=" + phone + ", shopImg=" + shopImg + ", priority=" + priority + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + ", shopStatus=" + shopStatus + ", advice=" + advice + ", area="
				+ area + ", owner=" + owner + ", shopCategory=" + shopCategory + "]";
	}
}
