package com.pyh.shiro.config;

import com.pyh.shiro.shiroBean.UserRealm;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean(name = "filterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
//        filterFactoryBean.setSuccessUrl("/suc");
        return filterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm getUserRealm()
    {
        return new UserRealm();
    }





}
