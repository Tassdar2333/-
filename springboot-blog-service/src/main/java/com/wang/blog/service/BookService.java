package com.wang.blog.service;


import com.wang.blog.pojo.Book;
import com.wang.blog.pojo.Category;
import com.wang.blog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryService categoryService;

    public List<Book> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return bookRepository.findAll(sort);
    }

    public List<Book> searchBySomething(String key) {
        return bookRepository.findByTitleLikeOrAuthorLike("%" + key + "%", "%" + key + "%");

    }

    public void addOrUpdate(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookRepository.findByCategory(category);
    }

}
