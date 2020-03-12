package com.qhm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qhm.pojo.Sku;
import com.qhm.pojo.SpecOption;

/**
 * 
 * @author qhm
 *
 */
public interface SkuDao {
	
	
	List<Sku> list(Sku sku);
	
	Sku get(int id);
	
	// 添加sku
	int addSku(Sku sku);
	//添加对应sku的属性值
	int addSkuSpec(@Param("skuId") int skuId,@Param("so") SpecOption so);

	List<Sku> listBySpu(int spuId);
	
	
	
	
}
