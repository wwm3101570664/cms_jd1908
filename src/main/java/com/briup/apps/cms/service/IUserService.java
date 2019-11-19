package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.config.CustomerException;

public interface IUserService {
	 /**
     * @Description: 通过id查询
    */
    UserExtend findById(long id);

    /**
     * @Description: 查询所有
     */
    List<User> findAll();
    
    /** 
     * @Description: 级联查询所有
    */ 
    List<UserExtend> cascadeRoleFindAll();

    /**
     * @Description:  保存或更新
    */
    void saveOrUpdate(User baseUser) throws CustomerException;

    /** 
     * @Description: 更新状态
    */ 
    void changeStatus(long id,String status) throws CustomerException;
    
    /** 
     * @Description: 通过id删除
     */ 
    void deleteById(long id) throws CustomerException;
    
    /** 
     * @Description: 设置角色
    */ 
    void setRoles(long id, List<Long> roles);
}
