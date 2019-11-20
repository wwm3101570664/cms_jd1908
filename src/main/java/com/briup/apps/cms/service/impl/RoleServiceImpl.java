package com.briup.apps.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.RoleExample;
import com.briup.apps.cms.bean.Role_Privilege;
import com.briup.apps.cms.bean.Role_PrivilegeExample;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.config.CustomerException;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.Role_PrivilegeMapper;
import com.briup.apps.cms.dao.extend.RoleExtendMapper;
import com.briup.apps.cms.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
		@Resource
	    private RoleMapper RoleMapper;
	    @Resource
	    private RoleExtendMapper RoleExtendMapper;
	    @Resource
	    private Role_PrivilegeMapper role_PrivilegeMapper;
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

		@Override
		public void authorization(long roleId, List<Long> privilegeIds) {
			// 根据roleid查询出所有的权限
			Role_PrivilegeExample example  = new Role_PrivilegeExample();
			example.createCriteria().andRoleIdEqualTo(roleId);
			List<Role_Privilege> list = role_PrivilegeMapper.selectByExample(example);
			
			// 将list转换为privilegeIDs的集合
			List<Long> old_privileges = new ArrayList<>();
			for (Role_Privilege rp : list) {
				old_privileges.add(rp.getPrivilegeId());
			}
			// 依次判断privilegeIds 是否存在old_privilegeIds，如果不在则插入
			for (Long id : privilegeIds) {
				if(!old_privileges.contains(id)) {
					Role_Privilege rp = new Role_Privilege();
					rp.setRoleId(roleId);
					rp.setPrivilegeId(id);
					role_PrivilegeMapper.insert(rp);
				}
			}
			 // 依次判断 是否存在old_privilegeIds 是否存在privilegeIds，如果不存在删除
			for (Long id : old_privileges) {
				if(!privilegeIds.contains(id)) {
					
					Role_PrivilegeExample example1 = new Role_PrivilegeExample();
					//需要同时指定roleId和peivilegeId才能去删除桥表中的记录。
					example1.createCriteria().andRoleIdEqualTo(roleId).andPrivilegeIdEqualTo(id);
					role_PrivilegeMapper.deleteByExample(example1 );
				}
			}
			
		}

		
}
