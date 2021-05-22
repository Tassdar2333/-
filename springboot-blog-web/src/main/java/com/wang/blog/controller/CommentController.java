package com.wang.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wang.blog.page.MyCommentPage;
import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.Comment;
import com.wang.blog.pojo.MyComment;
import com.wang.blog.pojo.Notice;
import com.wang.blog.repository.CommentRepository;
import com.wang.blog.service.CommentService;
import io.swagger.annotations.Api;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/21 12:01
 */
@RestController
@Api(tags = "EsProductController",value = "评论")
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;


    @PostMapping("/comment")
    public Article post(@RequestBody String json){
        return commentService.commentPost(json);
    }

    /**
     * 获取文章下评论的数据 MyComment -》Author Comment
     * @param json
     * @return
     */
    @PostMapping("/comment/list")
    public List<MyComment> getMyCommentList(@RequestBody String json) {
        return commentService.getMyCommentList(json);
    }

    /**
     * 获取用户的所有评论
     * @param id
     */
    @GetMapping("/comment")
    public MyCommentPage getUserCommentList(Integer id, Integer currentPage){
        return commentService.getUserCommentList(id,currentPage-1);
    }

    @DeleteMapping("/comment/delete")
    public MyCommentPage delete(@RequestBody String json,Integer id,String username){
        return commentService.delete(json,id,username);
    }

    @PutMapping("/comment")
    public MyCommentPage update(@RequestBody String json){
        return commentService.updateComment(json);
    }


}
