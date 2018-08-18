package com.bob.o2o.utils;

import java.security.MessageDigest;

/** 
* @author bob 
* @version 创建时间：2018年8月16日 下午2:41:45 
* 类说明 MD5工具类
*/
public class MD5 {
	public static final String getMd5(String s) {
		//16进制数组
		char[] hexDigits = {'5','0','5','6','2','9','6','2','5','q',
				'b','l','e','s','s','y'};
		try {
			char[] str ;
			//将传入的字符串转换成byte数组
			byte[] strTemp= s.getBytes();
			//获取MD5加密对象
			MessageDigest instance = MessageDigest.getInstance("MD5");
			//传入需要加密的目标数组
			instance.update(strTemp);
			//获取加密后的数组
			byte[] md = instance.digest();
			int j = md.length;
			str = new char[j*2];
			int k =0;
			for(int i=0;i<j;i++) {
				byte temp = md[i];
				str[k++] = hexDigits[temp>>>4 & 0xf];
				str[k++] = hexDigits[temp & 0xf];
			}
			//转换成String返回
			return new String(str);
		}catch(Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(MD5.getMd5("2"));
	}
}
