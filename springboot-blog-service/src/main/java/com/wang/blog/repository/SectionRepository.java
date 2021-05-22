package com.wang.blog.repository;

import com.wang.blog.pojo.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 14:07
 */
public interface SectionRepository extends JpaRepository<Section,Integer> {

    void deleteByName(String name);

    void deleteSectionByIdIn(List<Integer> ids);
}
