package com.bob.o2o.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** 
* @author bob 
* @version 创建时间：2018年8月13日 下午9:29:20 
* 类说明 
*/
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:d:/o2o/image/");
	}
	
}
