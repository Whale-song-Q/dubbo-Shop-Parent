package com.qhm.dao;

import java.util.List;

import com.qhm.pojo.Brand;

/**
 * 
 * @author qhm
 *
 */
public interface BrandDao {

	 List<Brand> getList(Brand mohu);

	int add(Brand brand);

	/**
	 * 
	 * @param id
	 * @return
	 */
	int delete(int[] id);

	/**
	 * 修改
	 * @param Brand
	 * @return
	 */
	int update(Brand brand);
	
	Brand toUpdate(String id);
}
