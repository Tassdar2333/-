package com.wang.blog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyNoticePage;
import com.wang.blog.pojo.Notice;
import com.wang.blog.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/3 15:32
 */
@Service
public class NoticeServiceImp implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;



    /**
     * 查询公告列表
     * @return
     */
    @Override
    public List<Notice> queryAll() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        return noticeRepository.findAll(sort);
    }

    /**
     * 分页查询 公告 页面大小 为7
     * @param currentPage
     */
    public MyNoticePage listPage(Integer currentPage){
        //排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        Sort sort = Sort.by(orders);
        //分页查询
        Page<Notice> pages = noticeRepository.findAll(PageRequest.of(currentPage,7,sort));
        //自定义page
        MyNoticePage myPage = new MyNoticePage();
        myPage.setContentList(pages.getContent());
        myPage.setTotalPageSize(pages.getTotalPages());
        return myPage;

    }




    /**
     * 更新公告
     * @param json
     * @return
     */
    @Override
    public MyNoticePage update(String json) {
        JSONObject object = JSONObject.parseObject(json);
        Notice myNotice = JSON.toJavaObject(object.getJSONObject("noticeList"),Notice.class);
        noticeRepository.save(myNotice);
        return listPage(0);
    }

    /**
     * 添加公告
     * @param notice
     * @return
     */
    @Override
    public MyNoticePage addNotice(Notice notice) {
        noticeRepository.save(notice);
        return listPage(0);
    }

    /**
     * @Transactional 因为涉及到删除 所以这里需要开启事务
     * @param json 前端json数据
     * @param id 删除策略（批量删除 单个删除）
     * @return
     */
    @Transactional
    @Override
    public MyNoticePage delete(String json, Integer id) {
        //将json串转化为json对象
        JSONObject n = JSONObject.parseObject(json);
        //这里通过前端传过来的id来判断删除策略
        if(id == 0){
            //单删除
            Notice myNotice = JSON.toJavaObject(n.getJSONObject("noticeList"),Notice.class);
            noticeRepository.deleteById(myNotice.getId());
        }else if(id == 1){
            //批量删除
            List<Notice> noticeList = JSONObject.toJavaObject(n.getJSONArray("noticeList"),List.class);
            List<Integer> ids = new ArrayList<>();
            for(Object jsonObject : noticeList){
                Notice no = JSONObject.parseObject(jsonObject.toString(),Notice.class);
                ids.add(no.getId());
            }
            noticeRepository.deleteNoticeByIdIn(ids);

        }
        //返回第一页数据
        return listPage(0);
    }


}
