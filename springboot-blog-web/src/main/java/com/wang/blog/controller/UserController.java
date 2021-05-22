package com.wang.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyUserPage;
import com.wang.blog.pojo.User;
import com.wang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/25 16:07
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 更新用户信息
     * @param json
     * @return
     */
    @PutMapping("/user")
    public User update(@RequestBody String json){
        return userService.update(json);
    }

    /**
     * 获取用户名
     * @param id
     * @return
     */
    @GetMapping("/user/username")
    public String getUsername(Integer id){
        return userService.getUserName(id);
    }

    /**
     * 通过文章获取作者名
     * @param id
     * @return
     */
    @GetMapping("/user/articleId")
    public String getUsernameByArticleId(Integer id){
        return userService.getNameByArticleId(id);
    }

    @GetMapping("/user")
    public MyUserPage getAllUserList(Integer currentPage){
        return userService.getAllUserCurrentPage(currentPage-1);
    }

    /**
     * 更新密码
     * @param json
     * @return
     */
    @PutMapping("/password")
    public String updatePassword(@RequestBody String json){
        return userService.updatePassword(json);
    }

}
