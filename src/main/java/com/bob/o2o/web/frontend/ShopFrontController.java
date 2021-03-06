package com.bob.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author bob 
* @version 创建时间：2018年8月6日 上午8:53:55 
* 类说明 
*/
@Controller
@RequestMapping("/frontend")
public class ShopFrontController {
	
	//新增、编辑产品
	@GetMapping("/productoperational")
	public String productoperational() {
		return "shop/productoperational";
	}
	
	//店铺的注册、编辑操作
	@GetMapping("/shopoperational")
	public String shopForm() {
		return "shopoperational";
	}
	
	//查看店铺列表
	@GetMapping("/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}
	//商店管理页面
	@GetMapping("/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	//商品类别管理
	@GetMapping("/productcategorymanagement")
	public String shopCategoryManagement() {
		return "shop/productcategorymanagement";
	}
	//产品管理页面
	@GetMapping("/productmanagement")
	public String productmanagement() {
		return "shop/productmanagement";
	}
	
	//主页页面
	@GetMapping("/mainpage")
	public String mainPage() {
		return "view/mainpage";
	}
	//前端店铺列表
	@GetMapping("/viewshoplist")
	public String viewshopList() {
		return "view/viewshoplist";
	}
	//店铺详情
	@GetMapping("/shopdetail")
	public String shopDetail() {
		return "view/shopdetail";
	}
	//商品详情
	@GetMapping("/productdetail")
	public String productDetail() {
		return "view/productdetail";
	}
	//登陆页面
	@GetMapping("/login")
	public String login() {
		return "local/login";
	}
	//注册页面
	@GetMapping("/register")
	public String register() {
		return "local/register";
	}
}
