package com.wang.blog.controller;

import com.wang.blog.pojo.Result;
import com.wang.blog.pojo.User;
import com.wang.blog.service.UserService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/2 10:22
 */
@RestController
@ApiModel("注册接口")
@CrossOrigin
public class RegisterController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        System.out.println(user);
        Integer confirm = userService.register(user);
        if(confirm == 1){
            return new Result(200,"注册成功");
        }else{
            return new Result(201,"注册失败，已存在");
        }

    }

}
