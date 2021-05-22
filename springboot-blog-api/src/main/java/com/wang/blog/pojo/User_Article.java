package com.wang.blog.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/4 18:46
 */
@Data
@Table(name="user_article")
@Entity
public class User_Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Integer id;

    @Column(name="user_id")
    Integer user_id;

    @Column(name="article_id")
    Integer article_id;

}
