package com.information.platform.service;

import java.util.List;

import com.information.platform.bean.Permission;
import com.information.platform.bean.Role;
import com.information.platform.bean.dto.PermissionDto;
import com.information.platform.bean.dto.RoleDto;


public interface RoleService {
	List<Permission> getPermissions(Integer roleId,Integer style);
	
	List<Role> selectAll();
	
    RoleDto selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(Role record);
	
	int insert(Role record);
	
	int deleteByPrimaryKey(Integer id);
	
	 int selectIdByName(String roleName);
	 
	 List<Role>  fuzzySearch(String keyword);
	 
	 List<Permission>  getPermissionsNoStyle(Integer roleId);
}
