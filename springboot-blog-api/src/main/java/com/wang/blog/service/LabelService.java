package com.wang.blog.service;

import com.wang.blog.page.MyLabelPage;
import com.wang.blog.pojo.Label;
import com.wang.blog.pojo.Section;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:32
 */
public interface LabelService {

    MyLabelPage listPage(Integer currentPage);

    List<Label> list();

    MyLabelPage addLabel(Label label);

    MyLabelPage delete(String json, Integer id);

    MyLabelPage update(String json);

}
