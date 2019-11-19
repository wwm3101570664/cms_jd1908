package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.config.CustomerException;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Resource
	CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public Category findById(long id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}

	@Override
	public void deleteById(long id) {
			categoryMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void batchDeleteById(long[] ids) {
		if(ids.length==0) {
			throw new CustomerException("请选择想删除的栏目");
		}else {
			for (long id : ids) {
				this.deleteById(id);
			}
		}
		
	}

	@Override
	public void saveOrUpdate(Category category) {
			if(category.getId()!=null) {
				CategoryExample example = new CategoryExample();
				example.createCriteria().andNameEqualTo(category.getName());
				List<Category> list = categoryMapper.selectByExample(example);
				if(list.size()>0) {
					throw new CustomerException("栏目不能重复");
				}
				categoryMapper.updateByPrimaryKey(category);
			}else {
				categoryMapper.insert(category);
			}
		
	}

}
