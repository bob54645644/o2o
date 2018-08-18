package com.bob.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.o2o.dao.ProductCategoryMapper;
import com.bob.o2o.dao.ProductMapper;
import com.bob.o2o.dto.ProductCategoryExecution;
import com.bob.o2o.entity.ProductCategory;
import com.bob.o2o.enums.ProductCategoryStateEnum;
import com.bob.o2o.exceptions.ProductOperationException;
import com.bob.o2o.service.ProductCategoryService;

/** 
* @author bob 
* @version 创建时间：2018年8月8日 上午10:07:18 
* 类说明 
*/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductCategory> getProductCategoryByShopId(Long shopId) {
		// TODO Auto-generated method stub
		return productCategoryMapper.queryProductCategoryByShopId(shopId);
	}
	
	//批量添加商品类别
	@Override
	@Transactional
	public ProductCategoryExecution batchProductCategoryList(List<ProductCategory> productCategories) {
		if(productCategories !=null && productCategories.size()>0) {
			try {
				int effectedNum = productCategoryMapper.insertProductCategoryList(productCategories);
				if(effectedNum<=0) {
					throw new ProductOperationException("店铺类别创建失败");
				}else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			}catch(Exception e) {
				throw new ProductOperationException("batchProductCategoryList error:"+ e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution removeProductCategory(long productCategoryId, long shopId) {
		if(productCategoryId >0 && shopId >0) {
			
			//解除与tb_product的关联
			try {
				int effectedNum = productMapper.updateProductCategoryIdNull(productCategoryId);
				//使用<0是因为，有可能该商品类别没有商品与之对应
				if(effectedNum<0) {
					throw new ProductOperationException("商品类别更新失败！");
				}
			}catch(Exception e) {
				throw new ProductOperationException(e.getMessage());
			}
			
			//删除操作
			try {
				int effectedNum = productCategoryMapper.deleteProductCategory(productCategoryId, shopId);
				if(effectedNum >0) {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}else {
					throw new ProductOperationException("removeProductCategory 操作失败！");
				}
			}catch (Exception e) {
				throw new ProductOperationException(e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

}
