package com.bob.o2o.utils;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

/** 
* @author bob 
* @version 创建时间：2018年8月6日 下午9:32:45 
* 类说明 
*/
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String realVerifyCode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String inputVerityCode = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if(inputVerityCode==null || !realVerifyCode.equals(inputVerityCode)) {
			return false;
		}else {
			return true;
		}
	}
}
