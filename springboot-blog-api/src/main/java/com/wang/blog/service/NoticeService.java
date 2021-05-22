package com.wang.blog.service;

import com.wang.blog.page.MyNoticePage;
import com.wang.blog.pojo.Notice;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:20
 * 公告业务接口
 */
public interface NoticeService {

    List<Notice> queryAll();

    MyNoticePage update(String notice);

    MyNoticePage addNotice(Notice notice);

    MyNoticePage delete(String json,Integer id);

    MyNoticePage listPage(Integer currentPage);




}
