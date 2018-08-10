package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午7:48:35 
* 类说明 商品实体类
* 属性
* productId
* productName
* productDesc
* imgAddr
* normalPrice
* promotionPrice
* priority
* point
* createTime
* lastEditTime
* enableStatus
* List<ProductImg> productImgList
* ProductCategory productCategory
* Shop shop
*/

import java.util.Date;
import java.util.List;

public class Product {
	//主键ID
	private Long productID;
	//商品名
	private String productName;
	//商品描述
	private String productDesc;
	//简略图
	private String imgAddr;
	//原价
	private String normalPrice;
	//现价
	private String promotionPrice;
	//权重
	private Integer priority;
//	//商品积分
//	private Integer point;
	//创建时间
	private Date createTime;
	//最近一次修改时间
	private Date lastEditTime;
	//商品状态 0、下架 1、上架
	private Integer enableStatus;
	//商品详情图列表
	private List<ProductImg> productImgList;
	//商品类别
	private ProductCategory productCategory;
	//商铺类别
	private Shop shop;
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
//	public Integer getPoint() {
//		return point;
//	}
//	public void setPoint(Integer point) {
//		this.point = point;
//	}
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
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public List<ProductImg> getProductImgList() {
		return productImgList;
	}
	public void setProductImgList(List<ProductImg> productImgList) {
		this.productImgList = productImgList;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	

}
