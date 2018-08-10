package com.bob.o2o.dto;
/** 
* @author bob 
* @version 创建时间：2018年8月8日 上午9:59:49 
* 类说明  商品类别增删改的执行结果
*/

import java.util.List;

import com.bob.o2o.entity.ProductCategory;
import com.bob.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	
	private int state;
	
	private String stateInfo;
	
	private List<ProductCategory> productCategories;
	
	public ProductCategoryExecution() {
	}
	
	//失败时调用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//成功时调用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,
			List<ProductCategory> categories) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategories = categories;
		
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

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}
	

}
