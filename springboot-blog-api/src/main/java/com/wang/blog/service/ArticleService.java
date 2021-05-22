package com.wang.blog.service;

import com.wang.blog.page.MyArticlePage;
import com.wang.blog.pojo.Article;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:50
 * 文章业务接口
 */
public interface ArticleService {

    List<Article> queryAll();

    void updateArticleById(Integer id);

    MyArticlePage delete(String json,Integer id,String username);

    MyArticlePage postArticle(String json);

    MyArticlePage commonArticlePage(Integer currentPage);

    Article getArticleById(Integer id);

    MyArticlePage userCurrentArticleListPage(Integer id,String username);

    MyArticlePage findByArticleNameLike(String name,Integer currentPage);

    void viewCount(Integer id);

    List<Article> getPopularArticle();

}
