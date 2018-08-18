package com.bob.o2o.enums;
/** 
* @author bob 
* @version 创建时间：2018年8月16日 下午3:27:43 
* 类说明 
*/
public enum LocalAuthStateEnum {
	SUCCESS(1,"操作成功"),LOGINFAIL(-1,"密码或账号输入有误"),
	NULL_AUTH_INFO(-1001,"注册信息为空"),
	ONLY_ONE_ACCOUNT(-1002,"最多只能绑定一个本地账号")
	;
	private int state;
	private String stateInfo;
	private LocalAuthStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public static LocalAuthStateEnum stateOf(int state) {
		for(LocalAuthStateEnum las:values()) {
			if(las.getState()==state) {
				return las;
			}
		}
		return null;
	}
}
