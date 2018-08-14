package com.bob.o2o.web.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.o2o.dto.ShopExecution;
import com.bob.o2o.entity.Area;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.service.AreaService;
import com.bob.o2o.service.ShopCategoryService;
import com.bob.o2o.service.ShopService;
import com.bob.o2o.utils.HttpServletRequestUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月14日 下午4:27:52 
* 类说明 
*/
@RestController
@RequestMapping("/view")
public class ViewShopListController {
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopService shopService;
	
	
	//获取店铺列表的信息
	@GetMapping("/getshopsinfo")
	public Map<String,Object> getShopsInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		Long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		
		List<ShopCategory> shopCategoryList =null;
		if(parentId != -1) {
			//如果parentId存在，则取出二级shopCategory列表
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}else {
			try {
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
			return modelMap;
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}
	
	//根据店铺条件获取店铺列表
	@GetMapping("/getshoplist")
	public Map<String,Object> getShopList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		//获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//一页的数据条数
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//判断空值
		if((pageIndex >-1) && (pageSize>-1)) {
			//尝试获取一级类别Id
			Long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			//尝试获取二级类别Id
			Long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			//获取区域Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			//模糊查找的店铺名
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			//组合条件
			Shop shopCondition = compactShopContion(parentId,shopCategoryId,areaId,shopName);
			//根据条件查询店铺
			ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", se.getShoplist());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "index 不能为空");
		}
		return modelMap;
	}

	private Shop compactShopContion(Long parentId, Long shopCategoryId, int areaId, String shopName) {
		Shop shopCondition = new Shop();
		//查询某个一级shopCategory下面的所有二级shopCategory里面的店铺列表
		if(parentId !=-1L) {
			ShopCategory childCategory = new ShopCategory();
			ShopCategory parentCategory = new ShopCategory();
			parentCategory.setShopCategoryId(parentId);
			childCategory.setParent(parentCategory);
			shopCondition.setShopCategory(childCategory);
		}
		//查询某个二级shopCategory下面的店铺列表
		if(shopCategoryId !=-1L) {
			ShopCategory shopCategory = new ShopCategory();
			shopCondition.setShopCategory(shopCategory);
		}
		//查询某个区域下的店铺列表
		if(areaId !=-1) {
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		//模糊查询店铺名
		if(shopName !=null) {
			shopCondition.setShopName(shopName);
		}
		shopCondition.setShopStatus(1);
		return shopCondition;
	}

}
