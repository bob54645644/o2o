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
import com.bob.o2o.exceptions.ProductOperationException;
import com.bob.o2o.service.ProductService;
import com.bob.o2o.utils.ImageUtil;
import com.bob.o2o.utils.PageCalculator;
import com.bob.o2o.utils.PathUtil;

/**
 * @author bob
 * @version 创建时间：2018年8月8日 下午10:49:43 类说明
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductImgMapper productImgMapper;

	// 新增产品
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> ProductImgList)
			throws ProductOperationException {
		// product空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给product设置默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			// 有缩略图则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			// 插入数据库
			try {
				int effectedNum = productMapper.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败:" + e.toString());
			}
			// 有商品详情图则添加
			if (ProductImgList != null && ProductImgList.size() > 0) {
				addProductImgList(product, ProductImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	private void addProductImgList(Product product, List<ImageHolder> productImgList) {
		// 新建商品详情图，生成图片，初始化设置
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> pdList = new ArrayList<>();
		for (ImageHolder ih : productImgList) {
			if (ih != null) {
				String imgAddr = ImageUtil.generateNormalImg(ih, dest);

				ProductImg productImg = new ProductImg();
				productImg.setImgAddr(imgAddr);
				productImg.setCreateTime(new Date());
				productImg.setProductId(product.getProductId());
				pdList.add(productImg);
			}
		}
		// 插入数据库
		try {
			int effectedNum = productImgMapper.batchInsertProductImg(pdList);
			if (effectedNum <= 0) {
				throw new ProductOperationException("创建商品详情图失败");
			}
		} catch (Exception e) {
			throw new ProductOperationException("创建商品详情图失败");
		}
	}

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	// 修改商品
	@Override
	@Transactional
	public ProductExecution updateProduct(Product product, ImageHolder thumbnail, List<ImageHolder> ProductImgList)
			throws ProductOperationException {
		if (product.getProductId() == null || product.getShop() == null || product.getShop().getShopId() == null) {
			return new ProductExecution(ProductStateEnum.OFFLINE);
		}
		// 先处理缩略图，才有缩略图的地址
		if (thumbnail != null && thumbnail.getImageInputStream() != null && thumbnail.getImageName() != null) {
			try {
				handleThumbnail(product, thumbnail);
			} catch (Exception e) {
				throw new ProductOperationException("产品缩略图处理出错！");
			}
		}
		// 更新product数据库
		try {
			int effectedNum = productMapper.updateProduct(product);
			if (effectedNum <= 0) {
				throw new ProductOperationException("更新数据库失败！");
			}
		} catch (Exception e) {
			throw new ProductOperationException("更新数据库失败！");
		}
		// 处理详情图
		if (ProductImgList != null && ProductImgList.size() > 0) {
			// 查询之前是否有商品详情图
			List<ProductImg> nowImgList = productImgMapper.queryProductImgList(product.getProductId());
			if (nowImgList.size() > 0) {
				try {
					// 查出文件中的图片
					for (ProductImg pi : nowImgList) {
						if (pi.getImgAddr() != null) {
							ImageUtil.deleteFileOrPath(pi.getImgAddr());
						}
					}
					// 删除数据库中的信息
					int effected2 = productImgMapper.deleteProductImgByProductId(product.getProductId());
					if (effected2 <= 0) {
						throw new ProductOperationException("删除产品详情图失败！");
					}
				} catch (Exception e) {
					throw new ProductOperationException("删除产品详情图失败:" + e.getMessage());
				}
			}
			// 生成新的详情图
			List<ProductImg> newproductImgList = new ArrayList<>();
			try {
				for (ImageHolder ih : ProductImgList) {

					String relativePathtemp = ImageUtil.generateNormalImg(ih,
							PathUtil.getShopImagePath(product.getShop().getShopId()));
					ProductImg productImg = new ProductImg();
					productImg.setProductId(product.getProductId());
					productImg.setCreateTime(new Date());
					productImg.setImgAddr(relativePathtemp);
					newproductImgList.add(productImg);
				}
				int effectedNum3 = productImgMapper.batchInsertProductImg(newproductImgList);
				if (effectedNum3 <= 0) {
					throw new ProductOperationException("添加产品详情图失败！");
				}
			} catch (Exception e) {
				throw new ProductOperationException("添加产品详情图失败：" + e.getMessage());
			}
		}
		return new ProductExecution(ProductStateEnum.SUCCESS);
	}

	private void handleThumbnail(Product product, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		// 如果有旧的，就删除旧的缩略图

		Product tempProduct = productMapper.queryProductById(product.getProductId());
		if (tempProduct.getImgAddr() != null) {
			ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
		}
		// 生成新的缩略图
		String relativePath = ImageUtil.generateThumbnail(thumbnail,
				PathUtil.getShopImagePath(product.getShop().getShopId()));
		product.setImgAddr(relativePath);

	}
	
	//根据productId查询产品信息
	@Override
	public ProductExecution queryProductByid(long productId) {
		if(productId>0) {
			try {
				Product product = productMapper.queryProductById(productId);
				return new ProductExecution(ProductStateEnum.SUCCESS,product);
			}catch(Exception e) {
				throw new ProductOperationException(e.toString());
			}
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	//通过产品信息查询产品列表
	@Override
	public ProductExecution getProductList(Product productCondition,
			int pageIndex,int pageSize) {
		//换算成行数
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productMapper.queryProductList(productCondition, rowIndex,pageSize);
		int count = productMapper.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

}
