package com.wang.blog.repository;

import com.wang.blog.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 14:09
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "select  * from comment where from_uid = ?1 limit ?2,?3",nativeQuery = true)
    List<Comment> findCommentsByFrom_uid(Integer id,Integer currentPage,Integer pageSize);

    @Query(value = "select * from comment where from_uid = ?1",nativeQuery = true)
    List<Comment> findCommentsByFrom_uid(Integer id);

    void deleteCommentByIdIn(List<Integer> ids);

    @Transactional
    @Modifying
    @Query(value="delete from comment where id = ?1",nativeQuery = true)
    void deleteById(Integer id);

    @Transactional
    @Modifying
    @Query(value ="update comment set content = ?2 where id = ?1",nativeQuery = true)
    void updateCommentContent(Integer id,String content);

}
