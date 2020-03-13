package com.qhm.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qhm.comm.HgShopConstant;
import com.qhm.pojo.Sku;
import com.qhm.pojo.Spu;
import com.qhm.pojo.SpuEsVo;
import com.qhm.pojo.SpuVo;
import com.qhm.service.GoodsService;
import com.qhm.utils.ElSearchUtil;
import com.github.pagehelper.PageInfo;

/**
 * 首页
 * @author qhm
 * 
 */
@Controller
public class IndexController {
	
	@Reference
	GoodsService goodsService;
	// 搜索引擎
	@Autowired 
	ElSearchUtil<SpuEsVo> elUtils;
	
	@Autowired
	private RedisTemplate<String, PageInfo<Spu>> redisTemplate;
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @param catId
	 * @return
	 */
	@RequestMapping({"/","index"})
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="0") int catId) {
		// 获取商品的数据
		// 只有首页的数据才缓存
		if(page==1 && catId==0) {
			ValueOperations<String, PageInfo<Spu>> opsForValue = redisTemplate.opsForValue();
			//有缓存
			if(redisTemplate.hasKey(HgShopConstant.SPU_CACHE_KEY)) {
				PageInfo<Spu> pageInfo = opsForValue.get(HgShopConstant.SPU_CACHE_KEY);
				request.setAttribute("pageInfo", pageInfo);
				return "index";
			}else {
				// 从服务中获取数据
				PageInfo<Spu> pageInfo = goodsService.listSpu(1, new SpuVo());
				// 数据放入缓存当中
				//缓存30分钟
				opsForValue.set(HgShopConstant.SPU_CACHE_KEY, pageInfo,30,TimeUnit.MINUTES);
				request.setAttribute("pageInfo", pageInfo);
				return "index";
			}
		}
		
		PageInfo<Spu> listSpu = goodsService.listSpu(page, new SpuVo());
		request.setAttribute("pageInfo", listSpu);
		return "index";
	}
	
	/**
	 * 显示商品详情
	 * @param request
	 * @param spuId
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int spuId) {
		/**
		 * 获取详情
		 */
		// spu
		Spu spu = goodsService.getSpu(spuId);
		//sku 
		List<Sku> skuList = goodsService.listSkuBySpu(spuId);
		
		request.setAttribute("spu", spu);
		request.setAttribute("skus", skuList);
		System.out.println("skuList is " + skuList);
		return "detail";
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("search")
	public String search(HttpServletRequest request,
			String key,@RequestParam(defaultValue="1") int page) {
		
		AggregatedPage<SpuEsVo> aggregatedPage = elUtils.queryObjects(SpuEsVo.class, page, 10, new String[]{"goodsName","caption","brandName","categoryName"},key );
		//aggregatedPage.getContent();
		request.setAttribute("pageInfo", aggregatedPage);
		return "search";
		
	}
	
}
