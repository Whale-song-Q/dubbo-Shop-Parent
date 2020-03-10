package com.qhm.service;

import java.util.List;


import com.github.pagehelper.PageInfo;
import com.qhm.pojo.Brand;
import com.qhm.pojo.Category;
import com.qhm.pojo.Sku;
import com.qhm.pojo.Spu;
import com.qhm.pojo.SpuVo;

/**
 * 
 * @author qhm
 *  
 *  **** Dubbo 服务接口函数比要有非Void 的返回值********
 *   
 */
public interface GoodsService { 

	/**
	 * 
	 * @param firstChar 首字母
	 * @param page 页码
	 * @return
	 */
	int addBrand(Brand brand);
	int updateBrand(Brand brand);
	int deleteBrand(int[] id);
	Brand toUpdate(Integer id);
	PageInfo<Brand> listBrand(Brand mohu, int page);
	List<Brand> getAllBrands( ); 
	
	
	int addCategory(Category category);
	int updateCategory(Category category);
	int deleteCategory(Integer id);
	/**
	 * 
	 * @param firstChar 首字母
	 * @param page 页码
	 * @return
	 */
	PageInfo<Category> listCategory( String firstChar,int page); 
	/**
	 * 以树的形式显示列表
	 * @return
	 */
	List<Category> treeCategory(); 
	
	// spu的管理
	PageInfo<Spu>  listSpu(int page,SpuVo vo);
	int addSpu(Spu spu);
	int updateSpu(Spu spu);
	int deleteSpu(int id);
	Spu getSpu(int id);
	int deleteSpuBatch(int[] id);
	
	// sku的管理
		PageInfo<Sku>  listSku(int page,Sku sku);
		int addSku(Sku sku);
		Sku getSku(int id);//获取详情
		int updateSku(Sku sku);
		int deleteSku(int id);
		int deleteSkuBatch(int[] id);



	

}
