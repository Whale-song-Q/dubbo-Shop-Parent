package com.qhm.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhm.dao.BrandDao;
import com.qhm.dao.CategoryDao;
import com.qhm.dao.SkuDao;
import com.qhm.dao.SpuDao;
import com.qhm.pojo.Brand;
import com.qhm.pojo.Category;
import com.qhm.pojo.Sku;
import com.qhm.pojo.SpecOption;
import com.qhm.pojo.Spu;
import com.qhm.pojo.SpuVo;
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
	
	@Autowired
	SpuDao spuDao;
	
	@Autowired
	SkuDao skuDao;
	
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
	public Brand toUpdate(Integer id) {
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
	// spu的列表
		@Override
		public PageInfo<Spu> listSpu(int page, SpuVo vo) {
			// TODO Auto-generated method stub
			PageHelper.startPage(page, 10);
			
			return new PageInfo<Spu>(spuDao.list(vo));
		}

		@Override
		public int addSpu(Spu spu) {
			// TODO Auto-generated method stub
			return spuDao.add(spu);
		}

		@Override
		public int updateSpu(Spu spu) {
			// TODO Auto-generated method stub
			return spuDao.update(spu);
		}

		@Override
		public int deleteSpu(int id) {
			// TODO Auto-generated method stub
			return spuDao.delete(id);
		}

		@Override
		public int deleteSpuBatch(int[] ids) {
			// TODO Auto-generated method stub
			return spuDao.deleteSpuBatch(ids);
		}
		@Override
		public List<Brand> getAllBrands() {
			// TODO Auto-generated method stub
			return brandDao.listAll();
		}

		@Override
		public PageInfo<Sku> listSku(int page, Sku sku) {
			// TODO Auto-generated method stub
			PageHelper.startPage(page, 5);
			return new PageInfo<Sku>(skuDao.list(sku));
		}

		@Override
		public int addSku(Sku sku) {
			// TODO Auto-generated method stub
			//先加主表
			int cnt = skuDao.addSku(sku);
			List<SpecOption> specs = sku.getSpecs();
			for (SpecOption specOption : specs) {
				cnt += skuDao.addSkuSpec(sku.getId(),specOption);
			}
			
			return cnt;
		}

		@Override
		public Sku getSku(int id) {
			// TODO Auto-generated method stub
			return skuDao.get(id);
		}

		@Override
		public int updateSku(Sku sku) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int deleteSku(int id) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int deleteSkuBatch(int[] id) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Spu getSpu(int id) {
			// TODO Auto-generated method stub
			return spuDao.findById(id);
		}

}
