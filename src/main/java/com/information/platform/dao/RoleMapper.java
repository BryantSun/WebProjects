package com.information.platform.dao;
import com.information.platform.bean.Permission;
import com.information.platform.bean.Role;
import com.information.platform.bean.dto.PermissionDto;
import com.information.platform.bean.dto.RoleDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);


    RoleDto selectByPrimaryKey(Integer id);

    List<Role> selectAll();
    
    List<Role>  fuzzySearch(String keyword);

    int updateByPrimaryKey(Role record);
    
    int selectIdByName(String roleName);
    
  //根据角色id来获取角色的所有权限
    List<Permission> getPermissions(@Param("roleId") Integer roleId,@Param("style")Integer style);
    
     List<Permission>  getPermissionsNoStyle(Integer roleId);
    
}