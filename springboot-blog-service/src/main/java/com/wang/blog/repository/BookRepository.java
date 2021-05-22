package com.wang.blog.repository;


import com.wang.blog.pojo.Book;
import com.wang.blog.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByCategory(Category category);

    List<Book> findByTitleLikeOrAuthorLike(String keyword1, String keyword2);

}
