package com.wang.blog.page;

import com.wang.blog.pojo.Label;
import com.wang.blog.pojo.Notice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/7 10:11
 */
@Data
@NoArgsConstructor
public class MyLabelPage {

    private Integer totalPageSize;

    private List<Label> contentList;
}
