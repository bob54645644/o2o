package com.bob.o2o.web.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.o2o.entity.HeadLine;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.service.HeadLineService;
import com.bob.o2o.service.ShopCategoryService;

/** 
* @author bob 
* @version 创建时间：2018年8月13日 上午10:56:49 
* 类说明 
*/
@RestController
@RequestMapping("/view")
public class MainPageController {
	
	@Autowired
	private HeadLineService headLineService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	//初始化前端展示系统的主页信息，包括一级店铺列表和头条列表
	@GetMapping("/mainpageinfo")
	public Map<String,Object> mainPageInfo(){
		Map<String,Object> modelMap = new HashMap<>();
		//获取一级店铺列表
		List<ShopCategory> shopCategoryList = new ArrayList<>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//获取头条信息
		List<HeadLine> headLineList = new ArrayList<>();
		try {
			HeadLine headLine = new HeadLine();
			headLine.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLine);
			modelMap.put("headLineList", headLineList);
			modelMap.put("success", true);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		return modelMap;
	}
}
