package com.bob.o2o.dto;
/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午11:14:56 
* 类说明 处理图片流的中间类
*/

import java.io.InputStream;

public class ImageHolder {
	//图片名
	private String imageName;
	//图片的inputStream
	private InputStream imageInputStream;
	
	public ImageHolder(String imageName, InputStream imageInputStream) {
		super();
		this.imageName = imageName;
		this.imageInputStream = imageInputStream;
	}

	public String getImageName() {
		return imageName;
	}

	public InputStream getImageInputStream() {
		return imageInputStream;
	}
	
	

}
