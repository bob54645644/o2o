package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午7:26:17 
* 类说明  商品详情图片实体类
* 属性
* productImgId
* imgAddr
* imgDesc
* priority
* createTime
* productId
*/

import java.util.Date;

public class ProductImg {
	//主键ID
	private Long productImgId;
	//图片地址
	private String imgAddr;
	//图片简介
	private String imgDesc;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//属与的商品的ID
	private Long productId;
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	

}
