package com.wang.blog.config.shiro;

import com.wang.blog.filter.ShiroUserFilter;
import com.wang.blog.filter.StatelessAuthFilter;
import com.wang.blog.filter.URLPathMatchingFilter;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 14:31
 */
@Configuration
public class ShiroConfig {
    /**
     * shiro重点配置: subject
     *              securityManager
     *              Realm
     *           分别对应三个类:
     *              shiroFilterFactoryBean
     *              DefaultWebSecurityManager
     *              Realm 需自定义
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        /**
         * 设置安全管理器
         */
        factoryBean.setSecurityManager(manager);
//        System.out.println("自定义过滤器");
//        Map<String, Filter> filters = factoryBean.getFilters();
//        // 注意这里不要用Bean的方式，否则会报错
//        filters.put("user", new StatelessAuthFilter());
//        factoryBean.setFilters(filters);
        /**
         * 添加shiro内置过滤器
         *anon:无需认证 就可以访问
         *           authc:需要认证才能访问
         *           user: 必须拥有记住我功能才能用
         *           perms: 拥有对某个资源的权限才能访问
         *           role:拥有某个角色
         */
        Map<String,String> map = new HashMap<String,String>();
//        map.put("/login","anon");
//        map.put("/register","anon");
//        map.put("/forget","anon");
//        map.put("/admin/label/list","anon");
//        map.put("/article/common","anon");
//        map.put("/article/common","anon");
//        map.put("/article/common","anon");
//        map.put("/admin/*","authc");
        map.put("/**","anon");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
//        manager.setRememberMeManager();
        return manager;
    }

    /**
     * 注入自定义的Realm
     * @return
     */
    @Bean(name = "myRealm")
    public MyRealm getMyRealm(){
        return new MyRealm();
    }


    /**
     * 用于解决依赖循环
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }


}
