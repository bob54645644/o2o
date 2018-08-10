package com.bob;

import java.io.File;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bob.o2o.dao.AreaMapper;
import com.bob.o2o.entity.Area;
import com.bob.o2o.utils.ImageUtil;

/**
 * @author bob
 * @version 创建时间：2018年8月4日 上午11:07:00 类说明
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		// File file = new File("e:/ftp.png");
		// String newfilename = ImageUtil.generateThumbnail(file, "/test");
		// System.out.println(newfilename);
		// AreaMapper areaMapper = ctx.getBean(AreaMapper.class);
		// list.forEach(x->{
		// System.out.println(x);
		// });
	}

}
