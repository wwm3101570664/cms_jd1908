package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/findAll")
	public Message findAll() {
		List<Category> list = categoryService.findAll();
		return MessageUtil.success(list);
	}
	
	@GetMapping("/findById")
	public Message findById(long id) {
		Category cate = categoryService.findById(id);
		return MessageUtil.success(cate);
	}
	
	@GetMapping("/deleteById")
	public Message deleteById(long id) {
		categoryService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	
	@PostMapping("/batchDelete")
	public Message batchDelete(@RequestParam long[] ids) {
		categoryService.batchDeleteById(ids);
		return MessageUtil.success("批量删除成功");
	}
	
	@PostMapping("/saveOrUpdate")
	public Message saveOrUpdate(Category category) {
		categoryService.saveOrUpdate(category);
		return MessageUtil.success(category);
	}
}
