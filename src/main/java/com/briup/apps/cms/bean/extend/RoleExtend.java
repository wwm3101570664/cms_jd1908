package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;

public class RoleExtend extends Privilege{
	private List<Privilege> privileges;

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
}
