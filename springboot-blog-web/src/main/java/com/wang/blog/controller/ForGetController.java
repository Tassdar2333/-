package com.wang.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/6 23:05
 */
@RestController
@CrossOrigin
public class ForGetController {
    @Autowired
    UserService userService;

    @PutMapping("/forget")
    public void forget(@RequestBody String json){
        JSONObject n = JSONObject.parseObject(json);
        String userName  = n.getString("userName");
        String newPassword = n.getString("newPassword");
        userService.forgetUpdate(userName,newPassword);

    }
}
