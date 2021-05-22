package com.wang.blog.service;

import com.wang.blog.page.MySectionPage;
import com.wang.blog.pojo.Section;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:13
 * 分类业务接口
 */
public interface SectionService {

    MySectionPage listPage(Integer currentPage);

    List<Section> list();

    MySectionPage addSection(Section section);

    MySectionPage delete(String section,Integer id);

    MySectionPage update(String section);


}
