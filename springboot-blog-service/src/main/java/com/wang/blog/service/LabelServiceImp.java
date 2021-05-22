package com.wang.blog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyLabelPage;
import com.wang.blog.pojo.Label;
import com.wang.blog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/7 10:14
 */
@Service
public class LabelServiceImp implements LabelService{
    @Autowired
    LabelRepository labelRepository;

    @Override
    public MyLabelPage listPage(Integer currentPage) {
        //分页
        Page<Label> pages = labelRepository.findAll(PageRequest.of(currentPage,7));
        MyLabelPage myLabelPage = new MyLabelPage();
        myLabelPage.setTotalPageSize(pages.getTotalPages());
        myLabelPage.setContentList(pages.getContent());
        return myLabelPage;

    }

    @Override
    public List<Label> list() {
        return labelRepository.findAll();
    }

    /**
     * 添加标签
     * @param label
     * @return
     */
    @Override
    public MyLabelPage addLabel(Label label) {
        labelRepository.save(label);
        return listPage(0);
    }

    /**
     * 删除标签
     * @param json
     * @param id
     * @return
     */
    @Override
    public MyLabelPage delete(String json, Integer id) {
        /**
         * 通过来区分是批量删除还是单个删除
         */
        JSONObject n = JSONObject.parseObject(json);
        if(id == 0){
            //json中获取对象
            Label myLabel = JSON.toJavaObject(n.getJSONObject("labelList"),Label.class);
            labelRepository.deleteById(myLabel.getId());
        }else if(id == 1){
            List<Label> labelList = JSON.toJavaObject(n.getJSONObject("labelList"),List.class);
            //获取批量删除id数组
            List<Integer> ids = new ArrayList<>();
            for(Object jsonObject : labelList){
                Label label = JSONObject.parseObject(jsonObject.toString(),Label.class);
                ids.add(label.getId());
            }
            labelRepository.deleteLabelByIdIn(ids);
        }
        return listPage(0);
    }

    /**
     * 修改标签
     * @param json
     * @return
     */
    @Override
    public MyLabelPage update(String json) {
        Label label = JSONObject.parseObject(json,Label.class);
        labelRepository.save(label);
        return listPage(0);
    }
}
