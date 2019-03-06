package com.information.platform.service;

import java.util.List;

import com.information.platform.bean.Role;
import com.information.platform.bean.User;
import com.information.platform.bean.dto.UserDto;

public interface UserService {

	int deleteByPrimaryKey(Integer id);


    int insert(User record);

   UserDto selectByPrimaryKey(Integer id);

  
    List<UserDto> selectAll();
    
    
   User selectByName(String username);
    
    List<Role> getRoles(String username); 

    
    int updateByPrimaryKey(User record);
    
    List<UserDto> fuzzySearch(String keyword);
    
    List<User> checkName(String loginname);
    
}
