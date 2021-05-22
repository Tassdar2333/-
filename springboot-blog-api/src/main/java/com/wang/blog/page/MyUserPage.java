package com.wang.blog.page;

import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/5/7 1:26
 */
@Data
@NoArgsConstructor
public class MyUserPage {

    private Integer totalPageSize;

    private List<User> contentList;
}
