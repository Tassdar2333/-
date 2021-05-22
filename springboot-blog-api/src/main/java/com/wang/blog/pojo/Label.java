package com.wang.blog.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 9:30
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
