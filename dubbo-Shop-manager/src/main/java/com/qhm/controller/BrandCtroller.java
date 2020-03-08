package com.qhm.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qhm.pojo.Brand;
import com.qhm.service.GoodsService;

/**
 * 品牌管理
 * @author qhm
 *
 */
@Controller
@RequestMapping("brand")
public class BrandCtroller {
	
	@Reference
	GoodsService goodsService;
	
	
	
	@RequestMapping("list")
	public String list(@RequestParam(defaultValue="1") int page,Brand mohu,
			HttpServletRequest request) {
		PageInfo<Brand> pageInfo = goodsService.listBrand(mohu, page);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("mohu",mohu.getMohu());
		
		
		return "brand/list";
	}
	
	@RequestMapping("addBrand")
	@ResponseBody
	public String addBrand(Brand brand,
			HttpServletRequest request) {
		int addBrand = goodsService.addBrand(brand);
		return addBrand>0?"success":"false";
	}
	
	
	/**
	 * 删除规格
	 * @param request
	 * @param id  规格的id
	 * @return
	 */
	@RequestMapping("delAllbrand")
	@ResponseBody
	public String delAllbrand(HttpServletRequest request,@RequestParam(name="ids[]") int[] ids) {
		System.out.println("要删除的brand数据");
		for (int i : ids) {
			System.out.println(" i is " + i  );
		}
		//调用服务
		int delNum = goodsService.deleteBrand(ids);
		return delNum>0?"success":"false";
	}
	/**
	 * 修改规格
	 * @param request
	 * @param id  规格的id
	 * @return
	 */
	@RequestMapping("doUpdatebrand")
	@ResponseBody
	public String doUpdatebrand(Brand brand,HttpServletRequest request) {
		System.out.println("要修改的brand="+brand);
		int updateBrand = goodsService.updateBrand(brand);
		
		return updateBrand>0?"success":"false";
		
	}
	/**
	 * 修改规格
	 * @param request
	 * @param id  规格的id
	 * @return
	 */
	@RequestMapping("toUpdatebrand")
	@ResponseBody
	public Brand toUpdatebrand(String id,HttpServletRequest request) {
		System.out.println("要修改的brand的id="+id);
		Brand update = goodsService.toUpdate(id);
		System.err.println("查询出来的对象信息"+update);
		return update;
	}
	
	
	

}
