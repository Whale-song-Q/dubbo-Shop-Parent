package com.qhm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

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
	
	Brand toUpdate(Integer id);
	@Select("SELECT id,name,first_char as firstChar "
			+ " FROM hg_brand "
			+ "where deleted_flag=0"
			+ " ORDER BY name ")
	List<Brand> listAll();
}
