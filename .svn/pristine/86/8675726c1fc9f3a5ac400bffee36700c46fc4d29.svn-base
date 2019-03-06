package com.information.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.information.platform.bean.Role_Permission;
import com.information.platform.dao.Role_PermissionMapper;
import com.information.platform.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl  implements RolePermissionService{

	@Autowired
	private Role_PermissionMapper role_PermissionMapper;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		return role_PermissionMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int insert(Role_Permission record) {
		
		return role_PermissionMapper.insert(record);
	}

	@Override
	public Role_Permission selectByPrimaryKey(Integer id) {
		
		return role_PermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role_Permission> selectAll() {
		
		return role_PermissionMapper.selectAll();
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(Role_Permission record) {
		
		return role_PermissionMapper.updateByPrimaryKey(record);
	}

	@Transactional
	@Override
	public int deleteByRoleId(Integer roleId) {
		
		return role_PermissionMapper.deleteByRoleId(roleId);
	}

	@Transactional
	@Override
	public int deleteByPerId(Integer perId) {
		
		return role_PermissionMapper.deleteByPerId(perId);
	}
	
	

}
