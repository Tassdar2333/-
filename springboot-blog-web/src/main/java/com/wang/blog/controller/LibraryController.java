package com.wang.blog.controller;


import com.wang.blog.pojo.Book;
import com.wang.blog.pojo.Category;
import com.wang.blog.service.BookService;
import com.wang.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class LibraryController {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    // 查询所有
    @GetMapping("/books")
    public List<Book> list() throws Exception {
        return bookService.list();
    }

    //添加或更新
    @PostMapping("/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }

    //通过关键字查询
    @GetMapping("/search")
    public List<Book> searchBy(String keywords) {
//        System.out.println("进入搜索");
        return bookService.searchBySomething(keywords);
    }

    //删除操作
    @PostMapping("/delete")
    public void delete(@RequestBody Book book) throws Exception {
//        System.out.println("进入删除");
        bookService.deleteById(book.getId());
    }

    //通过点击侧边栏 按分类找出图书
    @GetMapping("/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }

    //初始化分类
    @GetMapping("/categories")
    public List<Category> listCategory() {
        System.out.println(categoryService.list());
        return categoryService.list();
    }

    @PostMapping("/covers")
    public String coverUpload(@RequestParam(name = "file") MultipartFile multipartFile) throws Exception {
        System.out.println("文件上传");
        //文件目录
        String folder = "D:/workspace/img";
        String filename = multipartFile.getOriginalFilename();
        System.out.println(filename);
        String prefix = filename.substring(filename.lastIndexOf('.') + 1);
        String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "." + prefix;
        File file = new File(folder, newFilename);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            String imgURL = "http://localhost:8443/api/file/" + file.getName();
            return imgURL;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

}
