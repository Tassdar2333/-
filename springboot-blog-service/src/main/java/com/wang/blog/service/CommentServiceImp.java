package com.wang.blog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyArticlePage;
import com.wang.blog.page.MyCommentPage;
import com.wang.blog.pojo.*;
import com.wang.blog.repository.ArticleRepository;
import com.wang.blog.repository.CommentRepository;
import com.wang.blog.repository.UserRepository;
import com.wang.blog.util.PageUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/21 11:57
 */
@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> queryAll() {
        return null;
    }

    @Override
    public void updateCommentById(Integer id) {

    }

    @Override
    public void deleteCommentById(Integer id) {

    }
    /**
     * 发布评论
     */
    @Override
    public Article commentPost(String json) {
        //先转化前端json数据
        JSONObject object = JSONObject.parseObject(json);
        //获取json对象转化为java对象
        Comment comment = JSON.toJavaObject(object.getJSONObject("comment"),Comment.class);
        //获取文章id
        Integer id = object.getObject("articleId",Integer.class);
        //查询文章
        Article article = articleRepository.findById(id).orElse(null);
        //将评论添加到文章中 建立连接
        Set<Comment> commentList = article.getCommentList();
        commentList.add(comment);
        article.setCommentList(commentList);
        articleRepository.save(article);
        System.out.println(articleRepository.findById(id).orElse(null).getLabelList());
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public List<MyComment> getMyCommentList(String json) {
        JSONObject n = JSONObject.parseObject(json);
        List<Comment> commentList = JSON.toJavaObject(n.getJSONArray("commentList"),List.class);
        List<MyComment> myCommentList = new ArrayList<>();
        for(Object jsonObject : commentList){
            Comment comment = JSONObject.parseObject(jsonObject.toString(),Comment.class);
            String commentator = userRepository.findById(comment.getFrom_uid()).orElse(null).getUsername();
            MyComment myComment = new MyComment(commentator,comment);
            myCommentList.add(myComment);
        }
        return myCommentList;

    }

    @Override
    public MyCommentPage getUserCommentList(Integer id,Integer currentPage) {
        Integer pageCount = 0;
        MyCommentPage  myCommentPage = new MyCommentPage();
        List<Comment> comments = commentRepository.findCommentsByFrom_uid(id);
        Integer count = comments.size();
        if (count % 8 == 0) {
            pageCount = count / 8;
        } else {
            pageCount = count / 8 + 1;
        }
        myCommentPage.setTotalPageSize(pageCount);
        myCommentPage.setContentList(commentRepository.findCommentsByFrom_uid(id,currentPage,8));
        return myCommentPage;
    }

    /**
     * 删除操作 通过id来判断 批量删除还是单个删除
     * @param json
     * @param id 0：单个删除 1：批量删除
     * @param username
     * @return
     */
    @Override
    public MyCommentPage delete(String json, Integer id,String username) {
        JSONObject n = JSONObject.parseObject(json);
        if(id == 0){
            Comment comment = JSON.toJavaObject(n.getJSONObject("commentList"), Comment.class);
            commentRepository.deleteById(comment.getId());
        }else if(id == 1){
            List<Comment> commentList = JSON.toJavaObject(n.getJSONObject("articleList"),List.class);
            //获取批量删除id数组
            List<Integer> ids = new ArrayList<>();
            for(Object jsonObject : commentList){
                Comment comment = JSONObject.parseObject(jsonObject.toString(),Comment.class);
                ids.add(comment.getId());
            }
            commentRepository.deleteCommentByIdIn(ids);
        }
        if(username != null){
            //用户删除评论
            return userCurrentCommentListPage(1,username);
        }else{
            //管理员删除评论
            System.out.println("管理员删除");
            return findAllCommentList(0);
        }
    }

    @Override
    public MyCommentPage findAllCommentList(Integer currentPage) {
        List<Sort.Order> orders = new ArrayList<>();
        //通过createTime 倒序查询
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        //排序对象
        Sort sort = Sort.by(orders);
        //分页
        Page<Comment> pages = commentRepository.findAll(PageRequest.of(currentPage,8,sort));
        //自定义page对象
        MyCommentPage myPage = new MyCommentPage();
        myPage.setContentList(pages.getContent());
        myPage.setTotalPageSize(pages.getTotalPages());
        return myPage;
    }

    /**
     * 更新评论
     * @param json
     * @return
     */
    @Override
    public MyCommentPage updateComment(String json) {
        JSONObject n = JSONObject.parseObject(json);
        Integer id = n.getInteger("id");
        String content = n.getString("content");
        String username = n.getString("username");
        commentRepository.updateCommentContent(id,content);
        return userCurrentCommentListPage(1,username);
    }

    /**
     * 通过用户名获取当前用户的评论列表
     * @param currentPage
     * @param username
     * @return
     */
    public MyCommentPage userCurrentCommentListPage(Integer currentPage,String username){
        User user = userRepository.findByUsername(username);
        MyCommentPage myCommentPage = getUserCommentList(user.getUser_id(),currentPage-1);
        return myCommentPage;
    }


}
