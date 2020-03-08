package com.qhm.dao;

import java.util.List;

import com.qhm.pojo.Category;

/**
 * 
 * @author qhm
 *
 */
public interface CategoryDao {

	 List<Category> tree();

	int add(Category category);

	/**
	 * 
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 修改
	 * @param category
	 * @return
	 */
	int update(Category category);
}
