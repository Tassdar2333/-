package com.wang.blog.config.shiro;

import com.wang.blog.pojo.User;
import com.wang.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 14:32
 * shiro的Realm
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getUsername());
        User dataUser = userService.findByUserName(token.getUsername());
        if(dataUser.getUsername() == null){
            return null;
        }
        return new SimpleAuthenticationInfo(dataUser,dataUser.getPassword(),"");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
