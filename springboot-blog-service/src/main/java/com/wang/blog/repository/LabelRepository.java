package com.wang.blog.repository;

import com.wang.blog.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/7 10:15
 */
public interface LabelRepository extends JpaRepository<Label,Integer> {
    void deleteLabelByIdIn(List<Integer> ids);
}
