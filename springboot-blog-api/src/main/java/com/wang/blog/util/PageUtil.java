package com.wang.blog.util;

import com.wang.blog.page.MyArticlePage;
import com.wang.blog.pojo.Article;

import java.util.List;

/**
 * 自定义分页工具
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/6 9:33
 */
public class PageUtil {
    /**
     * 开始分页
     *
     * @param list
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static MyArticlePage startPage(List<Article> list, Integer pageNum,
                                          Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List<Article> pageList = list.subList(fromIndex, toIndex);
        MyArticlePage myArticlePage = new MyArticlePage();
        myArticlePage.setTotalPageSize(pageCount);
        myArticlePage.setContentList(pageList);
        return myArticlePage;
    }


}