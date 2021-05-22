package com.wang.blog.page;

import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;
import java.util.List;

/**
 * @Auther wangchengyang
 * @Date 2021/5/6 10:44
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class MyCommentPage {
    private Integer totalPageSize;

    private List<Comment> contentList;
}
