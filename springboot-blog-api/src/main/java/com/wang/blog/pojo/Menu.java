package com.wang.blog.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 9:34
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String path;

    private String name;

    private Integer Parent_id;


}
