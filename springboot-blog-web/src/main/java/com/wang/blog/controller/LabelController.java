package com.wang.blog.controller;

import com.wang.blog.page.MyLabelPage;
import com.wang.blog.page.MySectionPage;
import com.wang.blog.pojo.Label;
import com.wang.blog.pojo.Section;
import com.wang.blog.service.LabelService;
import com.wang.blog.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/7 10:29
 */
@RestController
@CrossOrigin
public class LabelController {
    @Autowired
    LabelService labelService;

    @PostMapping("/admin/label")
    public MyLabelPage add(@RequestBody Label label){
        return labelService.addLabel(label);
    }

    @PutMapping("/admin/label")
    public MyLabelPage update(@RequestBody String json){
        return labelService.update(json);
    }

    @DeleteMapping("/admin/label")
    public MyLabelPage delete(@RequestBody String json,Integer id){
        return labelService.delete(json,id);
    }

    @GetMapping("/admin/label/page")
    public MyLabelPage listPage(Integer currentPage){
        return labelService.listPage(currentPage-1);
    }

    @GetMapping("/admin/label/list")
    public List<Label> list(){
        return labelService.list();
    }
}
