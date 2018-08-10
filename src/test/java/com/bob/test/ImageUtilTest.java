package com.bob.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.utils.ImageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午7:01:35 
* 类说明 
*/
public class ImageUtilTest {

	@Test
	@Ignore
	public void testFile() throws IOException {
		System.out.println("hello!");
		File file = new File("e:/testtest/test");
		if(!file.exists()) {
			file.mkdirs();
		}
		
	}
	@Test
	@Ignore
	public void testThum() {
//		File file = new File("e:/ftp.png");
//		String newfilename = ImageUtil.generateThumbnail(f, "/test");
//		System.out.println(newfilename);
	}
	@Test
	@Ignore
	public void testjson() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	}
	@Test
	@Ignore
	public void testdelete() {
		ImageUtil.deleteFileOrPath("/test/t.txt");
	}
	@Test
	@Transactional
	public void testAdd() throws FileNotFoundException {
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = new FileInputStream(file);
		ImageHolder holder = new ImageHolder(file.getName(), inputStream);
		String newfilename = ImageUtil.generateThumbnail(holder, "/test");
		System.out.println(newfilename);
//		throw new RuntimeException("test");
	}

}
