package com.bob.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.o2o.dto.ConstantForSuperAdmin;
import com.bob.o2o.entity.Area;
import com.bob.o2o.service.AreaService;



/** 
* @author bob 
* @version 创建时间：2018年8月5日 上午9:34:33 
* 类说明 
*/

@RestController
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	@Autowired
	private AreaService areaService;
	//获取所有区域信息
	@Transactional
	@GetMapping("/listarea")
	public Map<String, Object> addArea(HttpServletRequest request){
		logger.info("====start======");
		Map<String, Object> modelMap = new HashMap<>();
		List<Area> list = new ArrayList<>();
		try {
			list = areaService.getAreaList();
			modelMap.put(ConstantForSuperAdmin.PAGE_SIZE, list);
			modelMap.put(ConstantForSuperAdmin.TOTAL, list.size());
		}catch(Exception e) {
			e.printStackTrace();;
//			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		logger.error("test error!");
		logger.info("=====end=====");
		return modelMap;
	}
	
	
	
}
