package com.wang.blog.controller;

import com.wang.blog.page.MySectionPage;
import com.wang.blog.pojo.Section;
import com.wang.blog.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/6 15:10
 */
@RestController
@CrossOrigin
public class SectionController {
    @Autowired
    SectionService sectionService;

    @PostMapping("/admin/section")
    public MySectionPage add(@RequestBody Section section){
       return sectionService.addSection(section);
    }

    @PutMapping("/admin/section")
    public MySectionPage update(@RequestBody String json){
        return sectionService.update(json);
    }

    @DeleteMapping("/admin/section")
    public MySectionPage delete(@RequestBody String json,Integer id){
        return sectionService.delete(json,id);
    }

    @GetMapping("/admin/section/page")
    public MySectionPage listPage(Integer currentPage){
        System.out.println(currentPage);
        return sectionService.listPage(currentPage-1);
    }

    @GetMapping("/admin/section/list")
    public List<Section> list(){
        return sectionService.list();
    }



}
