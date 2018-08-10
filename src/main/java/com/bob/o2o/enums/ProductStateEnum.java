package com.bob.o2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午10:30:16 
* 类说明 
*/
public enum ProductStateEnum {
	SUCCESS(1,"操作成功"),DOWN(0,"下架"),OFFLINE(-1,"非法商品"),
	INNER_ERROR(-1001,"内部错误"),EMPTY(-1002,"商品为空");
	private int state;
	private String stateInfo;
	private ProductStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public static ProductStateEnum stateOf(int state) {
		for(ProductStateEnum ps:values()) {
			if(ps.getState() == state) {
				return ps;
			}
		}
		return null;
	}
}
