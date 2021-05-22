package com.wang.blog.service;

import com.wang.blog.page.MyCommentPage;
import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.Comment;
import com.wang.blog.pojo.MyComment;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:48
 * 评论业务接口
 */
public interface CommentService {

    /**
     * 查询所有
     * @return
     */
    List<Comment> queryAll();

    /**
     * 更新内容 待定 可能没有更改 只有删除
     * @param id
     */
    void updateCommentById(Integer id);

    /**
     * 删除
     * @param id
     */
    void deleteCommentById(Integer id);

    /**
     * 评论发布
     * @param
     */
    Article commentPost(String json);

    /**
     * 获取评论列表 用于用户对于评论的管理
     * @param
     */
    List<MyComment> getMyCommentList(String json);

    /**
     * 获取当前用户所有评论
     * @param id
     * @param currentPage
     * @return
     */
    MyCommentPage getUserCommentList(Integer id, Integer currentPage);

    /**
     * 删除操作
     * @param json
     * @param id
     * @param username
     * @return
     */
    MyCommentPage delete(String json,Integer id,String username);

    /**
     * 管理员获取所有的评论
     * @param currentPage
     * @return
     */
    MyCommentPage findAllCommentList(Integer currentPage);

    /**
     * 更新评论
     * @param json
     * @return
     */
    MyCommentPage updateComment(String json);
}
