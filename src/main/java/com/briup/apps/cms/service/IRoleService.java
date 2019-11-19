package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.config.CustomerException;

public interface IRoleService {
	/** 
     * @Description: 查询所有角色 
    */ 
    List<Role> findAll();
    
    /** 
     * @Description: 查询角色级联权限
    */ 
    List<RoleExtend> cascadePrivilegeFindAll();
    
    /** 
     * @Description: 保存或更新角色信息 
    */ 
    void saveOrUpdate(Role Role) throws CustomerException;
    
    /** 
     * @Description: 通过id删除角色信息 
    */ 
    void deleteById(long id) throws CustomerException;
}
