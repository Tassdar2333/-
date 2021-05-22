package com.wang.blog.controller;


import com.wang.blog.page.MyNoticePage;
import com.wang.blog.pojo.Notice;
import com.wang.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/3 15:38
 */
@RestController
@CrossOrigin
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    /**
     * 新增公告
     * @param notice
     * @return
     */
    @PostMapping("/admin/notice")
    public MyNoticePage add(@RequestBody Notice notice){
        System.out.println(notice);
        return noticeService.addNotice(notice);
    }

    /**
     * 获取公告列表
     * @return
     */
    @GetMapping("/admin/notice")
    public MyNoticePage list(Integer currentPage){
        return noticeService.listPage(currentPage-1);
    }

    @GetMapping("/admin/notice/list")
    public List<Notice> listPage(){
        return noticeService.queryAll();
    }


    /**
     * 删除操作
     * @param notice
     * @param id
     * @return
     */
    @DeleteMapping("/admin/notice")
    public MyNoticePage deleteBatch(@RequestBody String notice,@PathParam("id") Integer id){
        return noticeService.delete(notice,id);
    }

    /**
     * 更新公告
     * @param notice
     * @return
     */
    @PutMapping("/admin/notice")
    public MyNoticePage update(@RequestBody String notice){
       return noticeService.update(notice);
    }


}
