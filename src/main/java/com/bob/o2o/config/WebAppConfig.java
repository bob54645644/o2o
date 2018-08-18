package com.bob.o2o.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bob.o2o.interceptor.ShopAdminLoginInterceptor;

/** 
* @author bob 
* @version 创建时间：2018年8月13日 下午9:29:20 
* 类说明 
*/
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ShopAdminLoginInterceptor shopAdminLoginInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:d:/o2o/image/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shopAdminLoginInterceptor).addPathPatterns("/frontend/**")
		.excludePathPatterns("/frontend/login");
	}
	
	
}
