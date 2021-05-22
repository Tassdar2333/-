package com.wang.blog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MySectionPage;
import com.wang.blog.pojo.Notice;
import com.wang.blog.pojo.Section;
import com.wang.blog.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/6 14:57
 */
@Service
public class SectionServiceImp implements SectionService{

    @Autowired
    SectionRepository sectionRepository;

    /**
     * 管理分类 分页处理
     * @param currentPage
     * @return
     */
    @Override
    public MySectionPage listPage(Integer currentPage) {
        Page<Section> pages = sectionRepository.findAll(PageRequest.of(currentPage,7));
        MySectionPage mySectionPage = new MySectionPage();
        mySectionPage.setTotalPageSize(pages.getTotalPages());
        mySectionPage.setContentList(pages.getContent());
        return mySectionPage;
    }

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public List<Section> list() {
        return sectionRepository.findAll();
    }

    @Override
    public MySectionPage addSection(Section section) {
        sectionRepository.save(section);
        return listPage(0);
    }

    @Transactional
    @Override
    public MySectionPage delete(String json, Integer id) {
        JSONObject n = JSONObject.parseObject(json);
        if(id == 0){
            Section mySection = JSON.toJavaObject(n.getJSONObject("sectionList"),Section.class);
            sectionRepository.deleteById(mySection.getId());
        }else if(id == 1){
            List<Notice> noticeList = JSONObject.toJavaObject(n.getJSONArray("sectionList"),List.class);
            List<Integer> ids = new ArrayList<>();
            for(Object jsonObject : noticeList){
                Section se = JSONObject.parseObject(jsonObject.toString(),Section.class);
                ids.add(se.getId());
            }
            sectionRepository.deleteSectionByIdIn(ids);
        }
        return listPage(0);
    }

    @Override
    public MySectionPage update(String section) {
        Section mySection = JSONObject.parseObject(section,Section.class);
        sectionRepository.save(mySection);
        return listPage(0);
    }

}
