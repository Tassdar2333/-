package com.wang.blog.repository;

import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.User_Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/4 18:49
 */
public interface User_ArticleRepository extends JpaRepository<User_Article,Integer> {
    @Query(value = "select u from User_Article u where u.article_id = ?1")
    User_Article findUser_ArticleByArticle_id(Integer id);
}
