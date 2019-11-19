package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: cms_jd1908
 * @description: 文章控制器类
 * @author: charles
 * @create: 2019-11-12 15:00
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("findAll")
    public Message findAll(){
        List<Article> list = articleService.findAll();
        return MessageUtil.success(list);
    }

    @GetMapping("cascadeFindAll")
    public Message cascadeFindAll(){
        List<ArticleExtend> list = articleService.cascadeFindAll();
        return MessageUtil.success(list);
    }

    @GetMapping("findById")
    public Message findById(long id){
        ArticleExtend articleExtend = articleService.findById(id);
        return MessageUtil.success(articleExtend);
    }
    
    @ApiOperation("保存或修改")
    @PostMapping("/saveOrUpdate")
    @ApiImplicitParams({
    		@ApiImplicitParam(name="title",value="标题",required = true,paramType ="form"),
    		@ApiImplicitParam(name="content",value="内容",required = true,paramType ="form"),
    		@ApiImplicitParam(name="source",value="内容",required = true,paramType ="form")
    	}
    		)
    public Message saveOrUpdate(
//    		@ApiParam(value = "编号",required = false) @RequestParam(value="id",required = false)Long id,
//    		@ApiParam(value = "标题" ,required = true) @RequestParam(value="title")String title,
//    		@ApiParam(value = "内容" ,required = true) @RequestParam(value="content")String content,
//    		@ApiParam(value = "源内容" ,required = true) @RequestParam(value="source")String source,)
//    		@ApiParam(value = "栏目编号" ,required = true) @RequestParam(value="categoryId")long categoryId,
//    		@ApiParam(value = "作者标号" ,required = true) @RequestParam(value="authorId")long authorId) 
    		Long id,String title,String content,String source)
    {
    	Article acticle = new Article();
    	acticle.setId(id);
    	acticle.setTitle(title);
    	acticle.setContent(content);
    	acticle.setSource(source);
//    	acticle.setCategoryId(categoryId);
//    	acticle.setAuthorId(authorId);
    	articleService.saveOrUpdate(acticle);
    	
    	//articleService.saveOrUpdate(article);
    	return MessageUtil.success("更新成功");
    }
    
    @GetMapping("/deleteById")
    public Message deleteById(long id) {
    	articleService.deleteById(id);
    	return MessageUtil.success("删除成功");
    }
    
    

}
