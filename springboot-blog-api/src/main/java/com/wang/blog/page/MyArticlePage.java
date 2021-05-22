package com.wang.blog.page;

import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.Label;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/8 14:01
 */
@Data
@NoArgsConstructor
public class MyArticlePage {

    private Integer totalPageSize;

    private List<Article> contentList;
}
