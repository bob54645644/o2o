package com.bob.o2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午7:16:06 
* 类说明 商品类别
* 属性
* productCategoryId
* shopId
* productCategoryName
* priority
* createTime
*/

import java.util.Date;

public class ProductCategory {
	//主键ID
	private Long productCategoryId;
	//该商品类别属与哪个店铺
	private Long shopId;
	//商品类别名称
	private String productCategoryName;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setproductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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
	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productCategoryId + ", shopId=" + shopId
				+ ", productCategoryName=" + productCategoryName + ", priority=" + priority + ", createTime="
				+ createTime + "]";
	}

}
