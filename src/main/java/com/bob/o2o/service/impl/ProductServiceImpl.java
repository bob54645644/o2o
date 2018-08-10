package com.bob.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.o2o.dao.ProductImgMapper;
import com.bob.o2o.dao.ProductMapper;
import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ProductExecution;
import com.bob.o2o.entity.Product;
import com.bob.o2o.entity.ProductImg;
import com.bob.o2o.enums.ProductStateEnum;
import com.bob.o2o.execeptions.ProductOperationException;
import com.bob.o2o.service.ProductService;
import com.bob.o2o.utils.ImageUtil;
import com.bob.o2o.utils.PathUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 下午10:49:43 
* 类说明 
*/
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper produtMapper;
	@Autowired
	private ProductImgMapper productImgMapper;
	
	//新增产品
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> ProductImgList)
			throws ProductOperationException {
		//product空值判断
		if(product !=null && product.getShop() !=null 
				&& product.getShop().getShopId() !=null) {
			//给product设置默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			//有缩略图则添加
			if(thumbnail !=null) {
				addThumbnail(product,thumbnail);
			}
			//插入数据库
			try {
				int effectedNum = produtMapper.insertProduct(product);
				if(effectedNum<=0) {
					throw new ProductOperationException("创建商品失败");
				}
			}catch(Exception e) {
				throw new ProductOperationException("创建商品失败:"+e.toString());
			}
			//有商品详情图则添加
			if(ProductImgList !=null && ProductImgList.size()>0) {
				addProductImgList(product,ProductImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS,product);
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
		
	}
	private void addProductImgList(Product product, List<ImageHolder> productImgList) {
		//新建商品详情图，生成图片，初始化设置
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> pdList = new ArrayList<>();
		for(ImageHolder ih:productImgList) {
			String imgAddr = ImageUtil.generateNormalImg(ih, dest);
			
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setCreateTime(new Date());
			productImg.setProductId(product.getProductID());
			pdList.add(productImg);
		}
		//插入数据库
		try {
			int effectedNum = productImgMapper.batchInsertProductImg(pdList);
			if(effectedNum <=0) {
				throw new ProductOperationException("创建商品详情图失败");
			}
		}catch(Exception e) {
			throw new ProductOperationException("创建商品详情图失败");
		}
	}
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

}
