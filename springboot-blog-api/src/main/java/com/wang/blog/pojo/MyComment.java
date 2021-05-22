package com.wang.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/5 10:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyComment {

    private String commentator ;

    private Comment comment;
}
