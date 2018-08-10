package com.bob.test;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.o2o.dao.ShopMapper;
import com.bob.o2o.entity.Area;
import com.bob.o2o.entity.PersonInfo;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.entity.ShopCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午3:48:50 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopMapperTest {
	@Autowired
	private ShopMapper shopMapper;
	
	@Test
	@Ignore
	public void testAInsertShop() {
		Shop shop = new Shop();
		shop.setShopName("8-7");
		shop.setShopDesc("好喝，养胃。");
		shop.setShopAddr("新疆");
		shop.setPhone("110");
		shop.setPriority(11);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setShopStatus(0);
		shop.setAdvice("没有建议。");
		
		Area area = new Area();
		area.setAreaId(1);
		shop.setArea(area);
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		shop.setOwner(personInfo);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shop.setShopCategory(shopCategory);
		shop.setShopImg("test");
		System.out.println(shop);
		
//		System.out.println(shop);
		int i = shopMapper.insertShop(shop);
		System.out.println(shop);
		System.out.println("插入条目数："+i);
		
	}
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopName("粥店1");
//		shop.setShopDesc("好喝，养胃。");
//		shop.setShopAddr("新疆");
		shop.setPhone("120");
		shop.setPriority(11);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setShopStatus(0);
		shop.setAdvice("好好干活。");
		
		Area area = new Area();
		area.setAreaId(1);
		shop.setArea(area);
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		shop.setOwner(personInfo);
		
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shop.setShopCategory(shopCategory);
		shop.setShopImg("正式的图片");
		
		shop.setShopId(1L);
		
		int i = shopMapper.updateShop(shop);
		System.out.println("更新的条目数："+i);
	}
	@Test
	@Ignore
	public void testQueryByShopId() {
		Shop shop = shopMapper.queryByShopId(1L);
		System.out.println(shop);
	}
	@Test
	public void testQueryShopList() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		
		Area area = new Area();
		area.setAreaId(2);
		
		ShopCategory shopCategory = new ShopCategory();
//		shopCategory.setShopCategoryId(1L);
		ShopCategory shopCategory2 = new ShopCategory();
		shopCategory2.setShopCategoryId(3L);
		shopCategory.setParent(shopCategory2);
		
		Shop shop = new Shop();
//		shop.setShopCategory(shopCategory);
//		shop.setArea(area);
//		shop.setShopName("店");
//		shop.setShopStatus(1);
//		shop.setOwner(personInfo);
		int count = shopMapper.queryShopCount(shop);
		System.out.println("总数："+count);
		List<Shop> shopList = shopMapper.queryShopList(shop, 10, 5);
		System.out.println(shopList.size());
	}
}
