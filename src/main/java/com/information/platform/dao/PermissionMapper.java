package com.information.platform.dao;

import com.information.platform.bean.Permission;
import com.information.platform.bean.dto.PermissionDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PermissionMapper {
   
    int deleteByPrimaryKey(Integer id);

    int deleteByPId(Integer pId);
    
    int insert(Permission record);

    
    Permission selectByPrimaryKey(Integer id);

    Permission selectById(Integer id);
    
    Permission selectByNavCode(String  navCode);
    
    List<Permission> selectAll();

    List<Permission> selectSonPermissions(Integer id);
    
    int updateByPrimaryKey(Permission record);
}