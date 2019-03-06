package com.information.platform.bean.dto;

import java.util.List;

import com.information.platform.bean.Permission;
import com.information.platform.bean.Role;

public class RoleDto extends Role{
     //权限id数组
	 private String permissions;
     //权限对象列表
	 private List<Permission> permissionlist;
	public List<Permission> getPermissionlist() {
		return permissionlist;
	}

	public void setPermissionlist(List<Permission> permissionlist) {
		this.permissionlist = permissionlist;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
