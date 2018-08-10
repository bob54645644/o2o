package com.bob.o2o.utils;
/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午5:28:56 
* 类说明 根据操作系统的不同，返回相应的上传图片的存储路径
*/
public class PathUtil {
	public static String separator = System.getProperty("file.separator");
	
	//返回项目图片的根路径
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="d:/o2o/image";
			//
		}else {
			basePath="/home/bob/o2o/image";
		}
//		basePath.replace("/", separator);
		return basePath;
	}
	//返回项目图片的子路径
	public static String getShopImagePath(long shopId) {
		String imgPath= "/upload/item/shop/"+shopId;
//		return imgPath.replace("/", separator);
		return imgPath;
	}
}
