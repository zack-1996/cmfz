package com.baizhi.cmfz.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

// 1.声明一个配置类 @Configuration 类
@Configuration
public class ShiroFilter {
    //2.声明一个@Bean 对象 交由Spring工厂管理 需要的Bean对象为过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        //可以 通过ShiroFilterFactoryBean 配置整个shiro 过滤器
        // 3创建一个shiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //5配置过滤器链 注意1.使用LinkedHashMap 2.要求将anon过滤声明写在前面
        LinkedHashMap linkedHashMap= new LinkedHashMap<>();
        // 注意： 如出现静态资源拦截问题 会network显示302 错误
        linkedHashMap.put("/img/**","anon");
        linkedHashMap.put("/boot/**","anon");
        linkedHashMap.put("/echarts/**","anon");
        linkedHashMap.put("/jqgrid/**","anon");
        linkedHashMap.put("/kindeditor/**","anon");
        linkedHashMap.put("/upload/**","anon");
        // 将登录方法 放行
        linkedHashMap.put("/admin/login","anon");
        // 6将过滤器链交友shiroFilterFactoryBea管理
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        // 7设置登录URl
        shiroFilterFactoryBean.setLoginUrl("/jsp/login.jsp");
        // 8 将DefaultWebSecuritymanager 对象
        return null;

    }
}
