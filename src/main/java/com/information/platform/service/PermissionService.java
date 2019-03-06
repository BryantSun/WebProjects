package com.information.platform.service;

import java.util.List;
import com.information.platform.bean.Permission;
import com.information.platform.bean.dto.PermissionDto;

public interface PermissionService {
	
	List<Permission> selectAll();

	int updateByPrimaryKey(Permission record);
	
	int insert(Permission record);
	
	int deleteByPrimaryKey(Integer id);
	
	int deleteByPId(Integer pId);
	
	Permission selectById(Integer id);
	  
	List<Permission> selectSonPermissions(Integer id);
	
	Permission selectByNavCode(String  navCode);
}
