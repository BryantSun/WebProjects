package com.information.platform.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.information.platform.bean.Permission;
import com.information.platform.bean.Role;
import com.information.platform.bean.User;
import com.information.platform.bean.dto.PermissionDto;
import com.information.platform.jwt.JwtToken;
import com.information.platform.service.RoleService;
import com.information.platform.service.UserService;
import com.information.platform.utils.JWTUtil;

/*
 * 自定义realm
 */
public class Myrealm extends AuthorizingRealm{
   
	@Autowired
    private UserService userService;
	
	@Autowired
    private RoleService roleService;
	
	
	/** * 大坑！，必须重写此方法，不然Shiro会报错
	 * */
	@Override 
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
		}
	
	//授权操作
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = com.information.platform.utils.JWTUtil.getUsername(principals.toString());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for(Role role : userService.getRoles(username) ){
			  info.addRole(role.getRolename());
			  for(Permission permission : roleService.getPermissions(role.getId(),1)){
				  info.addStringPermission(permission.getCode());
			  }
		}
		 	
		return info;
	}

	/**
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。 
	*/ 
	@Override
	 protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException { 
	String token = (String) auth.getCredentials(); 
	// 解密获得username，用于和数据库进行对比 
	String username = JWTUtil.getUsername(token); 
	if (username == null) { 
	throw new AuthenticationException("token invalid"); } 
	User userBean = userService.selectByName(username); 
	if (userBean == null) {
	 throw new AuthenticationException("User didn't existed!");
	 } 
	if (! JWTUtil.verify(token, username, userBean.getPassword())) {
	 throw new AuthenticationException("Username or password error");
	 } 
	return new SimpleAuthenticationInfo(token, token, "my_realm"); }
        
}
