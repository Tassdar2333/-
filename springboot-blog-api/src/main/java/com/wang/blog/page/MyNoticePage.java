package com.wang.blog.page;

import com.wang.blog.pojo.Notice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/6 10:08
 */
@Data
@NoArgsConstructor
public class MyNoticePage {

    /**
     * totalPageSize 总页数大小
     * contentList 公告列表
     */
    private Integer totalPageSize;

    private List<Notice> contentList;


}
