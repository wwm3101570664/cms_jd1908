package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.config.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

public interface IPrivilegeService {
	  
    /** 
     * @Description: 查询所有权限 
     */ 
    List<Privilege> findAll();
    
    /** 
     * @Description: 通过parentId查询权限
     */ 
    List<Privilege> findByParentId(Long parentId);
    
    /** 
     * @Description: 保存或更新权限 
     */ 
    void saveOrUpdate(Privilege privilege) throws CustomerException;

    List<PrivilegeTree> findPrivilegeTree();
}
