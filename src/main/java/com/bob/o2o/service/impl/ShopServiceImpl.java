package com.bob.o2o.service.impl;
/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午8:17:41 
* 类说明 
*/

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.o2o.dao.ShopMapper;
import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ShopExecution;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.enums.ShopStateEnum;
import com.bob.o2o.execeptions.ShopOperationException;
import com.bob.o2o.service.ShopService;
import com.bob.o2o.utils.ImageUtil;
import com.bob.o2o.utils.PageCalculator;
import com.bob.o2o.utils.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopMapper shopMapper;
	
	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder imageHolder) {
		if(null==shop) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setShopStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopMapper.insertShop(shop);
			if(effectedNum<=0) {
				throw new ShopOperationException("店铺创建失败");
			}else {
				if(imageHolder!=null && imageHolder.getImageName()!=null) {
					try {
						addShopImg(shop,imageHolder);
					}catch (Exception e) {
						throw new ShopOperationException("add shopImg error:"+e.toString());
						
					}
					int effectNum2 = shopMapper.updateShop(shop);
					if(effectNum2<=0) {
						throw new ShopOperationException("更新图片地址失败");
					}
					
				}
			}
			
		}catch(Exception e) {
			throw new ShopOperationException("add shop error:"+e.toString());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}


	private void addShopImg(Shop shop, ImageHolder imageHolder) {
		String shopImagePath = PathUtil.getShopImagePath(shop.getShopId());
		String path = ImageUtil.generateThumbnail(imageHolder, shopImagePath);
		shop.setShopImg(path);
	}

	//修改店铺
	@Override
	@Transactional
	public ShopExecution modifyShop(Shop shop,ImageHolder imageHolder) {
		if(null==shop || null==shop.getShopId()) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			//如果重新上传了缩略图，则删除原来的
			try {
				if(imageHolder !=null && imageHolder.getImageName()!=null
						&& !"".equals(imageHolder.getImageName())
						&& imageHolder.getImageInputStream() !=null) {
					Shop tempShop = shopMapper.queryByShopId(shop.getShopId());
					if(tempShop.getShopImg()!=null) {
						//删除原来的图片
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					//保存新图片
					addShopImg(shop, imageHolder);
				}
				//更新信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopMapper.updateShop(shop);
				if(effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else {
					shop = shopMapper.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			}catch(Exception e) {
				throw new ShopOperationException("modifyshop error:"+e.getMessage());
			}
		}
		
	}


	@Override
	public Shop getByShopId(Long shopId) {
		// TODO Auto-generated method stub
		return shopMapper.queryByShopId(shopId);
	}


	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		//将页码转换成行数
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		//依据查询条件，查询返回店铺列表
		List<Shop> queryShopList = shopMapper.queryShopList(shopCondition, rowIndex, pageSize);
		//根据相同条件返回店铺总数
		int count = shopMapper.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(queryShopList != null) {
			se.setShoplist(queryShopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		
		return se;
	}

}
