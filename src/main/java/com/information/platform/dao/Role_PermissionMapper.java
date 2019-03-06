package com.information.platform.dao;

import com.information.platform.bean.Role_Permission;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface Role_PermissionMapper {
 
    int deleteByPrimaryKey(Integer id);


    int deleteByRoleId(Integer roleId);
    
    int deleteByPerId(Integer perId);
    
    int insert(Role_Permission record);

     
 
    Role_Permission selectByPrimaryKey(Integer id);

    
    List<Role_Permission> selectAll();

    int updateByPrimaryKey(Role_Permission record);
}