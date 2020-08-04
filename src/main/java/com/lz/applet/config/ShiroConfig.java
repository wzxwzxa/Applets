package com.lz.applet.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dell
 * @date 2020-06-10 21:26
 */
@Configuration
public class ShiroConfig {

    /**
     * ShiroFileFactoryBean : 3
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
        anon:无需认证就能访问
        authc: 必须认证了才能访问
        user: 必须拥有记住我功能才能用
        perms: 拥有对某个资源的权限才能访问
        role: 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");

        //让下面对这些请求需要认证
        //下面这些请求需要认证
        filterMap.put("backstageGetArticleList","authc");
        filterMap.put("backstageAddToArticle","authc");
        filterMap.put("backstageDeleteArticle","authc");
        filterMap.put("backstageGetVideoList","authc");
        filterMap.put("backstageAddVideo","authc");
        filterMap.put("backstageDeleteVideoById","authc");
        filterMap.put("backstageGetLecturerList","authc");
        filterMap.put("backstageAddToLecturer","authc");
        filterMap.put("deleteLecturerId","authc");
        filterMap.put("getRecommendList","authc");
        filterMap.put("deleteRecommend","authc");
        filterMap.put("userManagement","authc");
        filterMap.put("addUser","authc");
        filterMap.put("deleteUser","authc");
        filterMap.put("updateUser","authc");
        filterMap.put("getCommentAll","authc");
        filterMap.put("deleteCommentById","authc");

        //授权 。 如果未授权会跳转到未授权页面
        filterMap.put("/userManagement","perms[user:admin]");
//        filterMap.put("")

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/goLogin");
        //未授权的页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }


    /**
     * DafaulWebSecurityManager :2
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 、管理userRealm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    /**
     *  创建 realm 对象 ，需要自定义 :1
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 创建整合shiroDialect: 用来整合shiro thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
