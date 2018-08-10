package com.bob.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

import com.bob.o2o.dao.AreaMapper;
import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ShopExecution;
import com.bob.o2o.entity.Area;
import com.bob.o2o.entity.PersonInfo;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.service.ShopService;

/** 
* @author bob 
* @version 创建时间：2018年8月5日 上午7:13:10 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopServiceTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	@Ignore
	public void testAinsertShop() {
		Shop shop = new Shop();
		shop.setShopName("8-6");
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
		
		
		File file = new File("e:/ftp.png");
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageHolder holder = new ImageHolder(file.getName(), inputStream);
		ShopExecution addShop = shopService.addShop(shop,holder );
		System.out.println(addShop.getStateInfo());
	}
	
	@Test
	@Ignore
	public void testBUpdateShop() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(40L);
		shop.setShopName("8-7-1");
		shop.setShopDesc("好喝，养胃。");
		shop.setShopAddr("新疆");
		shop.setPhone("110120");
		
		//修改图片
		File file = new File("e:/a.jpg");
		FileInputStream fs = new FileInputStream(file);
		ImageHolder imageHolder = new ImageHolder(file.getName(), fs);
		
		ShopExecution modifyShop = shopService.modifyShop(shop, imageHolder);
		System.out.println(modifyShop.getStateInfo());
		System.out.println(modifyShop.getShop());
	}
	@Test
	public void TestQueryShopList() {
		Shop shop = new Shop();
		ShopExecution shopList = shopService.getShopList(shop, 0, 100);
		System.out.println(shopList.getShoplist().size());
	}
}
