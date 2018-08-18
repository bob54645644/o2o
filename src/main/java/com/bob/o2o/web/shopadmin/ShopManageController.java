package com.bob.o2o.web.shopadmin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ShopExecution;
import com.bob.o2o.entity.Area;
import com.bob.o2o.entity.PersonInfo;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.entity.ShopCategory;
import com.bob.o2o.enums.ShopStateEnum;
import com.bob.o2o.exceptions.ShopOperationException;
import com.bob.o2o.service.AreaService;
import com.bob.o2o.service.ShopCategoryService;
import com.bob.o2o.service.ShopService;
import com.bob.o2o.utils.CodeUtil;
import com.bob.o2o.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.multipart.MultipartResolver;


/** 
* @author bob 
* @version 创建时间：2018年8月5日 下午9:55:38 
* 类说明 
*/
@RestController
@RequestMapping("/shopadmin")
public class ShopManageController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private MultipartResolver multipartResolver;
	
//	@Autowired
//	private ProductCategoryService productCategoryService;
	
//	//根据shopId获取商品类别
//		@GetMapping("/getproductcategorybyshopid")
//		public Map<String, Object> getProductCategoryByShopId(HttpServletRequest request){
//			Map<String, Object> modelMap =  new HashMap<>();
//			Long shopId =(Long) request.getSession().getAttribute("shopId");
//			if(shopId >0) {
//				List<ProductCategory> pcList = productCategoryService.getProductCategoryByShopId(shopId);
//				modelMap.put("success", true);
//				modelMap.put("pcList",pcList);
//			}else {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", "shopId 为空");
//			}
//			return modelMap;
//		}
	
	//获得店铺管理信息
	@GetMapping("/getshopmanagementinfo")
	public Map<String,Object > getShopManagementInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		//从request中解析shopId，如果没有，就从session中获取，.....
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId <= 0){
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			//如果都获取不到，重定向到shoplist
			if(currentShopObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/frontend/shoplist");
			}else {
				Shop currentShop2 = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop2.getShopId());
			}
		}else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}
	
	//根据条件获取店铺列表信息
	@GetMapping("/getshoplist")
	public Map<String,Object> getShopList(HttpServletRequest request){
		Map<String ,Object> modelMap = new HashMap<>(); 
		//1、从session中获取user
		
//		//为了测试，在session硬编码创建一个用户
//		PersonInfo user = new PersonInfo();
//		user.setUserId(1L);
//		user.setName("boboooo");
//		request.getSession().setAttribute("user", user);
		
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if(user ==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "获取信息失败");
			return modelMap;
		}
		//2、 查询并返回数据
		try {
				Shop shopCondition = new Shop();
				shopCondition.setOwner(user);
				ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
				modelMap.put("shopList", se.getShoplist());
				modelMap.put("user", user);
				modelMap.put("success", true);
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		return modelMap;
	}
	
	//修改店铺
	@PostMapping("/modifyshop")
	public Map<String,Object> modifyShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		//验证验证码
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}
		
		//1、接收并转化相应的参数，包括店铺信息，图片
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		MultipartFile shopImg=null;
		if(multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = multipartResolver.resolveMultipart(request);
			shopImg = multipartHttpServletRequest.getFile("shopImg");
		}
		
		//2、修改店铺信息
		if(shop !=null && shop.getShopId() !=null) {
			ShopExecution se;
			try {
				//如果没有改图片
				if(shopImg == null) {
					se = shopService.modifyShop(shop, null);
				}else {//如果改了图片
					ImageHolder imageHolder = new ImageHolder(shopImg.getName(), shopImg.getInputStream());
					se = shopService.modifyShop(shop, imageHolder);
				}
				//查看处理图片后的状态
				if(se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success",true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			}catch(ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺Id");
			return modelMap;
		}
		
	}
	
	@GetMapping("/getshopbyid")
	public Map<String,Object> getShopById(HttpServletRequest request){
		Map<String ,Object> modelMap = new HashMap<>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId>-1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("success", true);
				modelMap.put("shop",shop);
				modelMap.put("areaList", areaList);
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg",e.toString());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg","empty shopId");
		}
		return modelMap;
	}
	
	@GetMapping("/getshopinitinfo")
	public Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<>();
		List<ShopCategory> shopCategoryList = new ArrayList<>();
		List<Area> areaList = new ArrayList<>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		return modelMap;
	}
	
	@PostMapping("/registershop")
	public Map<String,Object> registerShop(HttpServletRequest request){
		/*1、接收并转化相关的参数
		 * 2、注册店铺
		 * 3、返回结果
		 */
		
		Map<String,Object> modelMap = new HashMap<>();
		//验证码，
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}
		
//		1、接收并转化相关的参数
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		//解析json数据，转换为shop
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//处理上传文件
		//重构处理上传文件部分
		MultipartFile shopImg =  null;
		if(multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = multipartResolver.resolveMultipart(request);
			shopImg = multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传文件不能为空");
			return modelMap;
		}
//		2、注册店铺
		//3、返回结果
		if(shop!=null && shopImg!=null){
			//这里是从session中获取用户信息，前期阶段没有，因此暂时硬编码一个
//			PersonInfo owner = (PersonInfo)request.getSession().getAttribute("user");
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(1L);
			shop.setOwner(personInfo);
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
				se = shopService.addShop(shop,imageHolder);
				if(se.getState()==ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			}catch(ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
			
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
}
