package com.bob.o2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年8月8日 上午10:01:17 
* 类说明 
*/
public enum ProductCategoryStateEnum {
	SUCCESS(1,"创建成功"),INNER_ERROR(-1001,"操作失败"),EMPTY_LIST(-1002,"添加数目小于1");
	
	private int state;
	private String stateInfo;

	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static ProductCategoryStateEnum stateOf(int state) {
		for(ProductCategoryStateEnum pcs:values()) {
			if(pcs.getState()==state) {
				return pcs;
			}
		}
		return null;
	}
	

}
