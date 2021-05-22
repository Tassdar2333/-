package com.wang.blog.service;

import com.wang.blog.page.MyUserPage;
import com.wang.blog.pojo.User;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:05
 * 用户业务逻辑接口
 */

public interface UserService {

    //查询所有用户列表
    List<User> queryAll();

    //查询通过username
    User findByUserName(String username);

    //删除用户
    Integer deleteByUsername(String uesrname);

    //注册
    Integer register(User uesr);

    //登陆
    User login(User user);

    User update(String json);

    //通过id获取用户名
    String getUserName(Integer id);

    String getNameByArticleId(Integer id);

    void forgetUpdate(String username,String newPassword);

    MyUserPage getAllUserCurrentPage(Integer currentPage);

    String updatePassword(String json);
}
