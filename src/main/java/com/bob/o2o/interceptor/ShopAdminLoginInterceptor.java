package com.bob.o2o.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bob.o2o.entity.PersonInfo;

/** 
* @author bob 
* @version 创建时间：2018年8月17日 下午4:31:42 
* 类说明 
*/
@Component
public class ShopAdminLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = request.getSession().getAttribute("user");
		if(object !=null) {
			PersonInfo user =(PersonInfo)object;
			if(user !=null &&user.getUserId()!=null && user.getEnableStatus()==1) {
				return true;
			}
		}
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open('"+request.getContextPath()+"/frontend/login','_self')");
		out.println("</script>");
		out.println("</html>");
		return false;
	}
	
}
