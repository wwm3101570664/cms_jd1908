package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.RoleExample;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.config.CustomerException;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.extend.RoleExtendMapper;
import com.briup.apps.cms.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
		@Resource
	    private RoleMapper RoleMapper;
	    @Resource
	    private RoleExtendMapper RoleExtendMapper;
	    @Override
	    public List<Role> findAll() {

	        return RoleMapper.selectByExample(new RoleExample());
	    }

	    @Override
	    public List<RoleExtend> cascadePrivilegeFindAll() {
	        return RoleExtendMapper.selectAll();
	    }

	    @Override
	    public void saveOrUpdate(Role Role) throws CustomerException {
	        if(Role.getId()!=null){
	            RoleMapper.updateByPrimaryKey(Role);
	        } else {
	            RoleMapper.insert(Role);
	        }
	    }

	    @Override
	    public void deleteById(long id) throws CustomerException {
	        Role role = RoleMapper.selectByPrimaryKey(id);
	        if(role == null){
	            throw new CustomerException("要删除的角色不存在");
	        }
	        RoleMapper.deleteByPrimaryKey(id);
	    }
}
