package com.qhm.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhm.dao.BrandDao;
import com.qhm.dao.CategoryDao;
import com.qhm.pojo.Brand;
import com.qhm.pojo.Category;
import com.qhm.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author qhm
 *
 */
@Service(interfaceClass=GoodsService.class)
public class GoodsServiceImpl  implements GoodsService{
	
	@Autowired
	BrandDao brandDao;

	@Autowired
	CategoryDao catDao;
	@Override
	public int addBrand(Brand brand) {
		// TODO Auto-generated method stub
		
		return brandDao.add(brand);
	}

	@Override
	public int updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return brandDao.update(brand);
	}

	@Override
	public int deleteBrand(int[] id) {
		// TODO Auto-generated method stub
		return brandDao.delete(id);
	}

	@Override
	public Brand toUpdate(String id) {
		// TODO Auto-generated method stub
		return brandDao.toUpdate(id);
	}
	
	
	
	
	
	
	@Override
	public PageInfo<Brand> listBrand(Brand mohu, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 4);
		List<Brand> list = brandDao.getList(mohu);
		PageInfo<Brand> pageInfo= new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return catDao.add(category);
	}
 
	@Override
	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		return catDao.update(category);
	}

	@Override
	public int deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		return catDao.delete(id);
	}

	/**
	 * 
	 */
	@Override
	public PageInfo<Category> listCategory(String firstChar, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> treeCategory() {
		// TODO Auto-generated method stub
		return catDao.tree();
	}



}
