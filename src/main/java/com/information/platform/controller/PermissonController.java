package com.information.platform.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.information.platform.bean.Permission;
import com.information.platform.bean.ResponseBean;
import com.information.platform.bean.Role;
import com.information.platform.bean.User;
import com.information.platform.bean.dto.PermissionDto;
import com.information.platform.exception.CustomException;
import com.information.platform.service.PermissionService;
import com.information.platform.service.RolePermissionService;
import com.information.platform.service.RoleService;
import com.information.platform.service.UserService;
import com.information.platform.tree.TreeUtil;
import com.information.platform.utils.JWTUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
@RequestMapping("/permission")
@RestController
public class PermissonController {
      
	
	@Autowired
	private RoleService RoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@ApiOperation(value="获取角色下导航权限列表(树状)",notes="获取角色下导航权限列表(树状)")
	@RequestMapping("/getList")
	public ResponseBean getAll(){
		 Subject subject = SecurityUtils.getSubject();
		 String username = JWTUtil.getUsername(subject.getPrincipal().toString());
		 List<Role> roles = userService.getRoles(username);
		 List<Permission> permissions = new ArrayList<>();
		 for (Role role : roles) {
			permissions = RoleService.getPermissions(role.getId(),0);
			
		}
	
		     //不同角色下的权限去重
			 for (int i = 0; i < permissions.size() - 1; i++) {
		            for (int j = permissions.size() - 1; j > i; j--) {
		                if (permissions.get(j).equals(permissions.get(i))) {
		                	permissions.remove(j);
		                }
		            }
		        }
			 
			
		List<Permission> permission = new TreeUtil<Permission>().getChildTree(permissions);
		
		return new ResponseBean(true, 200,"hahhaha", permission);
		
	}
	
	@ApiOperation(value="新增权限信息",notes="新增权限信息")
	@RequiresPermissions("permission:create")
	@RequestMapping("/create")
	public ResponseBean create( Permission permissionDto){
		 int n = permissionService.insert(permissionDto);
		 if (n<0) {
		   return new ResponseBean(false, 500,"数据或者sql异常", null);	
		 }
           return new ResponseBean(true, 200,"[insert a permission successfully]", null);			
		 }
	
	@ApiOperation(value="更新权限信息",notes="更新权限信息")
	@RequiresPermissions("permission:update")
	@RequestMapping("/update")
	public ResponseBean update( Permission permissionDto){
		int n = permissionService.updateByPrimaryKey(permissionDto);
		 if (n<0) {
			   return new ResponseBean(false, 500,"数据或者sql异常", null);	
			 }
	           return new ResponseBean(true, 200,"[update a permission successfully]", null);			
			 }	
	
	

	 @ApiOperation(value="删除权限",notes="根据id删除权限")
	 @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "java.lang.String")
	 @RequiresPermissions("permission:delete")
	 @PostMapping("/delete")
	 public ResponseBean delete(String id){
		 String [] strings = id.split(",");
		 List<Integer> ids =  new ArrayList<>();
		 for (String string : strings) {
				Integer n = Integer.valueOf(string);
				ids.add(n);
			}
		 
		  for (Integer integer : ids) {
			  List<Integer> perIds = new ArrayList<>();
				List<Permission> list = permissionService.selectSonPermissions(integer);
				for (Permission permissionDto : list) {
					  perIds.add(permissionDto.getId());
				}
			//如果有子权限,并且父权限不为空时,先删除子权限
			if (list!=null && list.size()!=0 && permissionService.selectById(integer).getpId()!=null) {
				
				for (Integer integer2 : perIds) {
					int num = permissionService.deleteByPrimaryKey(integer2);
					if (num<=0) {
						throw new CustomException("删除子权限失败");
					}
					//删除子权限对应的角色权限表记录
					rolePermissionService.deleteByPerId(integer2);
				}
				 //删除权限
				 int num = permissionService.deleteByPrimaryKey(integer);
				 if (num<=0) {
					throw new CustomException("删除权限失败");
				}
				//删除权限对应的角色权限表记录
			   rolePermissionService.deleteByPerId(integer);
				
			}
		}
		      
		       
		  for (Integer integer : ids) {
			    List<Integer> perIds = new ArrayList<>();
				List<Permission> list = permissionService.selectSonPermissions(integer);
				for (Permission permissionDto : list) {
					  perIds.add(permissionDto.getId());
				}
				//如果有子权限,并且父权限为空时
				if (list!=null && list.size()!=0 && permissionService.selectById(integer).getpId()==null) {
					//判断子权限下有没有子权限
					for (Integer integer2 : perIds) {
						List<Permission> lists = permissionService.selectSonPermissions(integer2);
						 List<Integer> pers = new ArrayList<>();
						if (lists!=null && lists.size()!=0) {
							for (Permission permissionDto : lists) {
								  pers.add(permissionDto.getId());
							}
							//如果子权限下有子权限的话,先删除所有子权限
							for (Integer integer3 : pers) {
								int num1 = permissionService.deleteByPrimaryKey(integer3);
								if (num1<=0) {
									throw new CustomException("删除子权限的子权限失败!");
								}
								//删除所有子权限下的子权限对应的角色权限表记录
								rolePermissionService.deleteByPerId(integer3); 
							}
							//删除子权限
							int son = permissionService.deleteByPrimaryKey(integer2);
							if (son<=0) {
								 throw new CustomException("删除子权限失败!");
							}
						}else {
							//如果子权限下没有子权限的话,先删除子权限
							int son = permissionService.deleteByPrimaryKey(integer2);
							if (son<=0) {
								 throw new CustomException("删除子权限失败!");
							}
							//删除子权限对应的角色权限表中的记录
							rolePermissionService.deleteByPerId(integer2); 
						}
					} 
					int num = permissionService.deleteByPrimaryKey(integer);
					 if (num<=0) {
							throw new CustomException("删除权限失败");
						}
					   //删除权限对应的角色权限表记录
					   rolePermissionService.deleteByPerId(integer);
				}else if(list==null || list.size()==0){
					        permissionService.deleteByPrimaryKey(integer);
						   //删除权限对应的角色权限表记录
						   rolePermissionService.deleteByPerId(integer);
				  }
			}
		 
		  return new ResponseBean(true, 200,"[delete permissions successfully]", null);	
		 
	 }
	
	
	@ApiOperation(value="获取权限列表(树状)",notes="获取权限列表(树状)")
	@RequestMapping("/getPermissions")
	public ResponseBean getList(){
		List<Permission> permission = new TreeUtil<Permission>().getChildTree(permissionService.selectAll());    
		return new ResponseBean(true, 200,"[you are daidaishou]", permission);
		
	}
	
	
	@ApiOperation(value="获取权限信息",notes="根据id获取权限信息")
	@RequiresAuthentication
	@RequestMapping("/getInfo")
	public ResponseBean getInfo(Integer id){
	    
		Permission permission = permissionService.selectById(id);
		return new ResponseBean(true, 200,"[you are daidaishou]", permission);
		
	}
	
	/**
	 * 获取子权限列表
	 * @param id
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping("/getSonPermissions")
	public ResponseBean getSonPermissions(Integer id){
		List<Permission> permissions = permissionService.selectSonPermissions(id);
		return new ResponseBean(true, 200,"[you are daidaishou]", permissions);
		
	}
	
	
}
