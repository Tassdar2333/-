package com.wang.blog.controller;

import com.wang.blog.pojo.Result;
import com.wang.blog.pojo.User;
import com.wang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 15:22
 */
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody User user){
//        System.out.println("查询到的一堆数据" + userService.login(user));
        return userService.login(user);
    }
}
