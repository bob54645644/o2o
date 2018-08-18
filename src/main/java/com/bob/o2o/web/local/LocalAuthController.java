package com.bob.o2o.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.o2o.entity.LocalAuth;
import com.bob.o2o.entity.PersonInfo;
import com.bob.o2o.service.LocalAuthService;
import com.bob.o2o.utils.HttpServletRequestUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月16日 下午11:16:28 
* 类说明 本地账号，controller类
*/
@RestController
@RequestMapping("/local")
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;
	
	//暂时未实现，注册用户
	@PostMapping("/register")
	public Map<String,Object> register(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		return null;
	}
	
	//登陆用户
	@PostMapping("/login")
	public Map<String,Object> login(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		try {
			LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, password);
			if(localAuth !=null) {
				PersonInfo personInfo = localAuth.getPersonInfo();
				request.getSession().setAttribute("user", personInfo);
				modelMap.put("success", true);
				modelMap.put("localAuth", localAuth);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误！");
			}
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "登陆出错");
		}
		return modelMap;
	}
	//注销用户
	@GetMapping("/logout")
	public Map<String,Object> logout(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		request.getSession().removeAttribute("user");
		if(request.getSession().getAttribute("user")==null) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "注销失败");
		}
		return modelMap;
	}
}
