package com.wang.blog.repository;

import com.wang.blog.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 14:07
 */
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    void deleteArticleByIdIn(List<Integer> ids);

    List<Article> findByTitleLike(String name);

    @Transactional
    @Modifying
    @Query(value="update article set view_count = ?1 where id = ?2",nativeQuery = true)
    void updateView_count(Integer count,Integer id);

    @Query(value = "select * from article order by view_count desc limit 0,10",nativeQuery = true)
    List<Article> getPopuarArticleList();

}
