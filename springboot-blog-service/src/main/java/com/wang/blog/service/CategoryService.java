package com.wang.blog.service;


import com.wang.blog.pojo.Category;
import com.wang.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryRepository.findAll(sort);

    }

    public Category get(int id) {
        Category c = categoryRepository.findById(id).orElse(null);
        return c;
    }

}
