package com.bob.o2o.exceptions;
/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午8:23:28 
* 类说明 
*/
public class ShopOperationException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5632019270704070849L;

	public ShopOperationException(String msg) {
		super(msg);
	}
}
