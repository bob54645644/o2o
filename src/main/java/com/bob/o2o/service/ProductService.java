package com.bob.o2o.service;

import java.util.List;

import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ProductExecution;
import com.bob.o2o.entity.Product;
import com.bob.o2o.execeptions.ProductOperationException;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午10:23:03 
* 类说明 
*/
public interface ProductService {
	
	//新增产品时，product， 缩略图，详情图列表要一起传入进来
	ProductExecution addProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> ProductImgList)throws ProductOperationException;
	
	//修改产品
	ProductExecution updateProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> ProductImgList)throws ProductOperationException;
	
	//通过productId查询产品信息
	ProductExecution queryProductByid(long productId);
	
	//通过产品信息查询产品列表
	ProductExecution getProductList(Product productCondition,
			int pageIndex,int pageSize);

}
