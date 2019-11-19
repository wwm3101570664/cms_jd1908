package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.config.CustomerException;

public interface ICategoryService {
	public List<Category> findAll();
	public Category findById(long id);
	public void deleteById(long id) throws CustomerException;
	public void batchDeleteById(long[] ids) throws CustomerException;
	
	public void saveOrUpdate(Category category);

}
