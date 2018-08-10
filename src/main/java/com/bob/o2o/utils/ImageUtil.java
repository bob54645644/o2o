package com.bob.o2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午6:07:18 
* 类说明 处理上传图片工具类
*/

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bob.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	private static String watermarkPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	//由于test线程不能加载到水印图片，为了测试，先用绝对路径下的水印
	private static String watermartPath2= "e:";
	private static Random random = new Random();
	private static 	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	//将CommonsMultipartFIle转换成File
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile multipartFile) {
		File newFile = new File(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(newFile);
		}catch(IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}catch(IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	//处理缩略图
	public static String generateThumbnail(ImageHolder imageHolder,String targetAddr) {
		//生成随机文件名
		String realFileName = getRandomFileName();
		//获得扩展名
		String extension = getFileExtension(imageHolder);
		//生成文件夹
		makeDirPath(targetAddr);
		//获得文件相对存储路径(带文件名)
		String relativeAddr = targetAddr+realFileName+extension;
//		relativeAddr.replace("/", PathUtil.separator);
		
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		
		logger.debug("currnt complete addr is:"+PathUtil.getImgBasePath()+relativeAddr);
		
		try {
			Thumbnails.of(imageHolder.getImageInputStream()).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermartPath2+"/watermark.jpg")), 0.25f)
			.outputQuality(0.8f).toFile(dest);
		}catch(IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new RuntimeException("创建缩略图失败："+e.toString());
		}
		return relativeAddr;
		
	}
	//处理商品详情图
	public static String generateNormalImg(ImageHolder holder,String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(holder);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr +realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		logger.debug("current normalImg  addr is:"+PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(holder.getImageInputStream()).size(337, 640)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermartPath2+"/watermark.jpg")), 0.25f)
			.outputQuality(0.9f).toFile(dest);
		}catch(IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new RuntimeException("商品详情图创建失败："+e.toString());
		}
		return relativeAddr;
	}
	
	//删除文件及文件夹
	public static void deleteFileOrPath(String shopImg) {
		File file = new File(PathUtil.getImgBasePath()+shopImg);
		if(file.exists()) {
			if(file.isDirectory()) {
				File[] files = file.listFiles();
				for(File f:files) {
					f.delete();
				}
			}
			file.delete();
		}
	}


	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		File file = new File(PathUtil.getImgBasePath()+targetAddr);
		if(!file.exists()) {
			file.mkdirs();
		}
		
	}


	private static String getFileExtension(ImageHolder imageHolder) {
		// TODO Auto-generated method stub
		String fileName = imageHolder.getImageName();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		return extension;
	}

	
	private static String getRandomFileName() {
		// TODO Auto-generated method stub
		int randomNum = random.nextInt(89999)+10000;
		String newTimeStr = simpleDateFormat.format(new Date());
		return "/"+randomNum+newTimeStr;
	}
}
