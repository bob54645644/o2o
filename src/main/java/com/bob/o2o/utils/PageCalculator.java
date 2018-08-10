package com.bob.o2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月7日 下午4:00:26 
* 类说明 		将页码数转换为行数
*/
public class PageCalculator {
		public static int calculateRowIndex(int pageIndex,int pageSize) {
			return (pageIndex >0)?(pageIndex-1)*(pageSize):0;
		}
}
