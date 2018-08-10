package com.bob.o2o.dao;

import java.util.List;

import com.bob.o2o.entity.ProductImg;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午9:57:40 
* 类说明 
*/
public interface ProductImgMapper {
	//批量添加productImg
	int batchInsertProductImg(List<ProductImg> imgs);
	//通过productId获取productImg
	List<ProductImg> queryProductImgList(long productId);
	//删除产品对应的productImg
	int deleteProductImgByProductId(long productId);
}
