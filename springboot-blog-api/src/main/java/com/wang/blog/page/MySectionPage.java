package com.wang.blog.page;

import com.wang.blog.pojo.Section;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/6 14:51
 */
@Data
@NoArgsConstructor
public class MySectionPage {
    /**
     * totalPageSize 总页数大小
     * contentList 分类列表
     */
    private Integer totalPageSize;

    private List<Section> contentList;
}
