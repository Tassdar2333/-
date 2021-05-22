package com.wang.blog.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/7 14:17
 */
@Data
@Table(name="user_role")
@Entity
public class User_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Integer id;

    @Column(name="user_id")
    Integer user_id;

    @Column(name="role_id")
    Integer article_id;
}
