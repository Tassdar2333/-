package com.wang.blog.repository;

import com.wang.blog.pojo.User_Article;
import com.wang.blog.pojo.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/7 14:17
 */
public interface User_RoleRepository extends JpaRepository<User_Role,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into user_role (user_id,role_id) values(?1,?2)",nativeQuery = true)
    void registerAdd(Integer uid,Integer rid);
}
