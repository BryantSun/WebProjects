package com.information.platform.shiro;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.information.platform.jwt.JwtFilter;
@Configuration
public class ShiroConfiguration {
     //把自定义realm加入到spring容器
	@Bean
	public Myrealm  getMyrealm() {
		Myrealm myrealm = new Myrealm();
		return myrealm;
	}
	
	//安全管理器的配置
	@Bean("securityManager")
	public SecurityManager getSecurityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(getMyrealm());
		//关闭Shiro自带的session
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		securityManager.setSubjectDAO(subjectDAO);
		return securityManager;
	}
	
	//filter工厂,用来设置过滤条件以及跳转条件
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean (SecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		Map<String, Filter> filterMap = new HashMap<String, Filter>();
		filterMap.put("jwt", new JwtFilter());
		bean.setSecurityManager(securityManager);
		bean.setFilters(filterMap);
		Map<String,String> map = new HashMap<String,String>();
		map.put("/**", "jwt");
	
		//认证失败跳转url
		bean.setUnauthorizedUrl("/401");
		bean.setFilterChainDefinitionMap(map);
		
		return bean;
		
	}
	
	/** 
	* 下面的代码是添加注解支持
	 */
	
	@Bean 
	@DependsOn("lifecycleBeanPostProcessor") 
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() { 
	DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
	 // 强制使用cglib，防止重复代理和可能引起代理出错的问题 
	defaultAdvisorAutoProxyCreator.setProxyTargetClass(true); 
	return defaultAdvisorAutoProxyCreator;
	 } 

	@Bean
	 public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
	 return new LifecycleBeanPostProcessor(); 
	}

	
	
	
	
	//加入注解的使用,不加入注解无效
	@Bean
	public  AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
}
