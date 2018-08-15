package com.bob.o2o.web.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.o2o.dto.ProductExecution;
import com.bob.o2o.entity.Product;
import com.bob.o2o.service.ProductService;
import com.bob.o2o.utils.HttpServletRequestUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月15日 下午5:19:37 
* 类说明 
*/
@RestController
@RequestMapping("/view")
public class ProductDetailController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/productdetailinfo")
	public Map<String,Object> productDetailInfo(HttpServletRequest request){
		Map<String,Object> modeMap = new HashMap<>();
		Long productId = HttpServletRequestUtil.getLong(request, "productId");
		Product product = null;
		if(productId != -1L) {
			ProductExecution pe = productService.queryProductByid(productId);
			modeMap.put("success", true);
			modeMap.put("product", pe.getProduct());
		}else {
			modeMap.put("success", false);
			modeMap.put("errMsg", "productId为空");
		}
		return modeMap;
	}
}
