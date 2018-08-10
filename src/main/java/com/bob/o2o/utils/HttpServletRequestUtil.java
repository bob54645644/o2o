package com.bob.o2o.utils;

import javax.servlet.http.HttpServletRequest;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午10:06:41 
* 类说明 	从request中获取相应参数的工具类
*/
public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request,String key) {
		try{
			return Integer.decode(request.getParameter(key));
		}catch(Exception  e) {
			return -1;
		}
	}
	public static Long getLong(HttpServletRequest request,String  key) {
		try{
			return Long.valueOf(request.getParameter(key));
		}catch(Exception  e) {
			return -1L;
		}
	}
	public static Double getDouble(HttpServletRequest request,String  key) {
		try{
			return Double.valueOf(request.getParameter(key));
		}catch(Exception  e) {
			return -1d;
		}
	}
	public static boolean getBoolean(HttpServletRequest request,String  key) {
		try{
			return Boolean.valueOf(request.getParameter(key));
		}catch(Exception  e) {
			return false;
		}
	}
	public static String  getString(HttpServletRequest request,String  key) {
		try{
			String result = request.getParameter(key);
			if(result!=null) {
				result = result.trim();//去掉两端的空格
			}
			if("".equals(result)) {
				result = null;
			}
			return result;
		}catch(Exception  e) {
			return null;
		}
	}

}
