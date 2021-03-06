package com.bob.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.bob.o2o.dto.ImageHolder;
import com.bob.o2o.dto.ProductCategoryExecution;
import com.bob.o2o.dto.ProductExecution;
import com.bob.o2o.entity.Product;
import com.bob.o2o.entity.ProductCategory;
import com.bob.o2o.entity.Shop;
import com.bob.o2o.enums.ProductCategoryStateEnum;
import com.bob.o2o.enums.ProductStateEnum;
import com.bob.o2o.exceptions.ProductOperationException;
import com.bob.o2o.service.ProductCategoryService;
import com.bob.o2o.service.ProductService;
import com.bob.o2o.utils.CodeUtil;
import com.bob.o2o.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bob
 * @version 创建时间：2018年8月8日 上午10:09:34 类说明 店铺管理员管理店铺中的产品
 */

@RestController
@RequestMapping("/shopadmin")
public class ProductManageController {

	private final int IMGMAXCOUNT = 6;
	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductService productService;

	// 处理图片流
	@Autowired
	private MultipartResolver multipartResolver;

	// 修改产品信息
	@PostMapping("/modifyproduct")
	public Map<String, Object> modifyproduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		//如果是上下架操作
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
		// 1、验证码校验
		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
			return modelMap;
		}

		Product product = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<>();

		// 2、处理product 信息
		try {
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop != null && currentShop.getShopId() != null) {
				String productStr = HttpServletRequestUtil.getString(request, "productStr");
				ObjectMapper mapper = new ObjectMapper();
				product = mapper.readValue(productStr, Product.class);
				product.setShop(currentShop);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "shopId为空！");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 获取图片信息,有图片信息则相应对象赋值
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = multipartResolver.resolveMultipart(request);

			// 3、处理缩略图
			try {
				MultipartFile thumbnailMultipart = multipartHttpServletRequest.getFile("thumbnail");
				if (thumbnailMultipart != null) {
					thumbnail = new ImageHolder(thumbnailMultipart.getOriginalFilename(),
							thumbnailMultipart.getInputStream());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			// 4、处理详情图列表
			try {
				for (int i = 0; i < 6; i++) {
					MultipartFile file = multipartHttpServletRequest.getFile("productImg" + i);
					if (file != null) {
						ImageHolder imageHolder = new ImageHolder(file.getOriginalFilename(), file.getInputStream());
						productImgList.add(imageHolder);
					} else {
						break;
					}
				}

			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		}
		// 5、提交
		try {
			ProductExecution pe = productService.updateProduct(product, thumbnail, productImgList);
			if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				return modelMap;
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStateInfo());
				return modelMap;
			}
		} catch (ProductOperationException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}

	// 通过productId获取产品信息，用于修改产品信息是用
	@GetMapping("/getproductbyid")
	public Map<String, Object> getProductById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			Long productId = HttpServletRequestUtil.getLong(request, "productId");
			ProductExecution pe = productService.queryProductByid(productId);
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop != null) {
				List<ProductCategory> productCategoryList = productCategoryService
						.getProductCategoryByShopId(currentShop.getShopId());
				modelMap.put("productCategoryList", productCategoryList);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "shopId为空");
				return modelMap;
			}
			if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("product", pe.getProduct());
				return modelMap;
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStateInfo());
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}
	
	//根据shopId查询产品列表
	@GetMapping("/getproductlistbyshopId")
	public Map<String,Object> getProductList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
		if(pageIndex >-1 && pageSize >-1 && currentShop !=null
				&& currentShop.getShopId() !=null) {
			Product productCondition = new Product();
			productCondition.setShop(currentShop);
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("success", true);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "查询条件不正确");
		}
		return modelMap;
	}
		
		
		
	@PostMapping("/addproduct")
	public Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 验证码
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		// 处理json数据
		ObjectMapper mapper = new ObjectMapper();

		Product product = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		// 若requet中存在图片流，处理
		try {
			if (multipartResolver.isMultipart(request)) {
				// 处理缩略图和详情图
				thumbnail = handleImage(request, thumbnail, productImgList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 转换json获取product
		try {
			String productStr = HttpServletRequestUtil.getString(request, "productStr");
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 处理product，以及保存图片
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);
				// 执行插入操作
				ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;
	}

	private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws IOException {
		MultipartHttpServletRequest multipartHttpServletRequest = multipartResolver.resolveMultipart(request);
		// 缩略图赋值
		MultipartFile thumbnailFile = multipartHttpServletRequest.getFile("thumbnail");
		thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
		// 详情图处理
		for (int i = 0; i < IMGMAXCOUNT; i++) {
			MultipartFile productImg = multipartHttpServletRequest.getFile("productImg" + i);
			if (productImg != null) {
				ImageHolder productImgHolder = new ImageHolder(productImg.getOriginalFilename(),
						productImg.getInputStream());
				productImgList.add(productImgHolder);
			} else {
				// 没有图片则提前退出循环
				break;
			}
		}
		return thumbnail;

	}

	// 根据shopId获取商品类别
	@GetMapping("/getproductcategorybyshopid")
	public Map<String, Object> getProductCategoryByShopId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if (currentShop != null && currentShop.getShopId() > 0) {
			List<ProductCategory> pcList = productCategoryService.getProductCategoryByShopId(currentShop.getShopId());
			modelMap.put("success", true);
			modelMap.put("pcList", pcList);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shopId 为空");
		}
		return modelMap;
	}
	

	// 批量添加商品类别
	@PostMapping("/addpclist")
	public Map<String, Object> batchPCList(@RequestBody List<ProductCategory> productCategorieList,
			HttpServletRequest request) {
		// 1、从session中获取当前店铺，得到shopId，设置到productCategorieList中
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		productCategorieList.forEach(x -> {
			x.setShopId(shop.getShopId());
			x.setCreateTime(new Date());
		});
		// 2、向数据库添加数据
		Map<String, Object> modelMap = new HashMap<>();
		if (productCategorieList != null && productCategorieList.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService.batchProductCategoryList(productCategorieList);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}

	// 通过productCategoryId和shopId删除productCategory
	@PostMapping("/removePC")
	public Map<String, Object> removePC(Long productCategoryId, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Shop shop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution execution = productCategoryService.removeProductCategory(productCategoryId,
						shop.getShopId());
				if (execution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", execution.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				// 异常里面要返回，要不然没有返回值，下次注意。
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "至少选择一个类别");
		}
		return modelMap;
	}

}
