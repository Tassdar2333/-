package com.wang.blog.repository;

import com.wang.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 13:58
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user u SET u.username = :#{#user.username},u.phone = :#{#user.phone},u.email = :#{#user.email},u.desc = :#{#user.desc} where user_id  = :#{#user.user_id}",nativeQuery = true)
    void updateUser(@Param("user")User user);

    @Transactional
    @Modifying
    @Query(value = "update user u set u.password = ?2 where username = ?1",nativeQuery = true)
    void forgetUpdate(String username,String password);

    @Transactional
    @Modifying
    @Query(value = "insert into user (username,password,phone,email,createtime) values(:#{#user.username},:#{#user.password},:#{#user.phone},:#{#user.email},NOW()) ",nativeQuery = true)
    public void saveUser(User user);
}
