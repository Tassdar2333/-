package com.wang.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyArticlePage;
import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.User;
import com.wang.blog.repository.ArticleRepository;
import com.wang.blog.repository.UserRepository;
import com.wang.blog.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;


/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/8 11:39
 */
@RestController
@CrossOrigin
public class ArticleController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    ArticleService articleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @PostMapping("/admin/article")
    public MyArticlePage postArticle(@RequestBody String json){
            logger.info("发布文章");
        return articleService.postArticle(json);

    }


    /**
     * 获取所有文章信息
     * @param currentPage
     * @return
     */
    @GetMapping("/article/common")
    public MyArticlePage commonArticleList(Integer currentPage){
        System.out.println("获取公共文章列表" + currentPage);
        return articleService.commonArticlePage(currentPage-1);
    }

    /**
     *
     * @param id 文章id
     * @param username 用户名
     * @return
     */
    @DeleteMapping("/admin/article")
    public MyArticlePage deleteArticle(@RequestBody String json,Integer id,String username){
        return articleService.delete(json,id,username);
    }

    /**
     * 获取单个文章信息
     * @param id
     * @return
     */
    @GetMapping("/article/information")
    public Article getArticleById(Integer id){
        if(id != null){
            return articleService.getArticleById(id);
        }
        return null;

    }

    /**
     * 当前用户的文章列表
     * @param currentPage
     * @param username
     * @return
     */
    @GetMapping("/article/user/list")
    public MyArticlePage userArticleList(Integer currentPage,String username){
        return articleService.userCurrentArticleListPage(currentPage,username);
    }

    @GetMapping("/article/search")
    public MyArticlePage searchByArticleName(@PathParam("name") String name,Integer currentPage){

       return articleService.findByArticleNameLike(name,currentPage);
    }

    /**
     * 更新浏览量
     * @param json
     */
    @PutMapping("/view_count")
    public void articleviewCount(@RequestBody String json){
        JSONObject n = JSONObject.parseObject(json);

        Integer id = n.getInteger("id");

        articleService.viewCount(id);
    }

    /**
     * 获取前10条受欢迎文章
     * @return
     */
    @GetMapping("/popular")
    public List<Article> getPopularArticle(){
        return articleService.getPopularArticle();
    }

}


