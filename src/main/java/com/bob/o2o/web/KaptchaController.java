package com.bob.o2o.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/** 
* @author bob 
* @version 创建时间：2018年8月3日 下午8:29:34 
* 类说明  生成验证码的类，是一个controller，其他方法调用这个路由即可。
* 需要加入相应的依赖
* 依赖：
* <dependency>
	<groupId>com.github.penggle</groupId>
	<artifactId>kaptcha</artifactId>
	<version>2.3.2</version>
  </dependency>
*/
@Controller
public class KaptchaController {
	@Bean
	public DefaultKaptcha getKaptcha() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();

		Properties properties = new Properties();

		properties.setProperty("kaptcha.border", "yes");
		properties.setProperty("kaptcha.border.color", "105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		properties.setProperty("kaptcha.image.width", "125");
		properties.setProperty("kaptcha.image.height", "45");
		properties.setProperty("kaptcha.textproducer.font.size", "45");
		properties.setProperty("kaptcha.session.key", "code");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");

		Config config = new Config(properties);
		kaptcha.setConfig(config);
		return kaptcha;
		
	}
	@Autowired
	private Producer producer;

	@GetMapping("/kaptcha")
	public void kaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
//		String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//		System.out.println(code);
		
		String text = producer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
//		System.out.println("controller："+text);
		BufferedImage image = producer.createImage(text);
		
		
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "jpg", outputStream);
		outputStream.flush();
//		return null;
	}
	
}
