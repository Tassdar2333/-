package com.wang.blog.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 13:53
 * 版块
 */
@Data
@Entity
@Table(name="section")
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //版块id
    @Column(name="id")
    private Integer id;
    //版块名
    @Column(name="name")
    private String name;
    //版块描述
    @Column(name="description")
    private String description;
}
