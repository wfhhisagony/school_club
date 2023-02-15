package com.example.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioConfig {

    //ShiroFilterFactoryBean : Step3
    @Bean
    public ShiroFilterFactoryBean getShrioFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anno: 无需认证就可以访问
            authc: 必须认证了才可以访问
            user: 必须拥有 记住我 功能才能用
            perms: 拥有对某个资源的权限才能访问
            role: 拥有某个角色权限才能访问
        */

        Map<String, String> filterMap = new LinkedHashMap<>();

        //用户授权,正常情况下没有授权会跳转到授权页面
        //filterMap.put("/user/add","perms[user:add]");
        //filterMap.put("/user/add","perms[4]");
        //filterMap.put("/user/update","perms[user:update]");

        //拦截  authc是请求认证，会跳转到bean.setLoginUrl的url页面
        //filterMap.put("/user/add","authc");
        //filterMap.put("/user/update","authc");
        //注意这个还会把css、js资源给拦截掉, 只要是访问admin的都会拦截掉
        filterMap.put("/admin/*","authc");
        filterMap.put("/member","authc");
        filterMap.put("/event/unchecked","authc");

        //设置登录请求
        bean.setLoginUrl("/user/login");  // 这里的url都是controller层中的
        //设置未授权页面
        bean.setUnauthorizedUrl("/noauth");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    //DefaultWebSecurityManager : Step2
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") com.example.demo.config.UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建 realm 对象, 需要自定义类：Step1
    @Bean
    public com.example.demo.config.UserRealm userRealm(){
        return new com.example.demo.config.UserRealm();
    }

    //整合 ShiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
