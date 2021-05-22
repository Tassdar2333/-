package com.wang.blog.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "cover")
    String cover;
    @Column(name = "title")
    String title;
    @Column(name = "author")
    String author;
    @Column(name = "date")
    String date;
    @Column(name = "press")
    String press;
    @Column(name = "abs")
    String abs;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;
}
