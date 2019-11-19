package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Test;
import com.briup.apps.cms.service.ITestService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
    private ITestService testService;
	
	@GetMapping("findAll")
	public List<Test> findAll(){
		 return testService.findAll();
	}
	
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(Test test) {
		testService.saveOrUpdate(test);
		return "更新成功";
	}
	
	
}
