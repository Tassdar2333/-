package com.wang.blog.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 13:48
 * 文章
 */
@Data
@NoArgsConstructor
@Entity //标识其是一个映射类
@Table(name = "article") //标识映射表
//开启监听用于创建时间自动写入
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //文章号
    @Column(name = "id")
    private Integer id;

    //标题
    @Column(name = "title")
    private String title;

    //文章内容
    @Column(name = "text")
    private String text;

    //是否删除
    @Column(name = "is_deleted")
    private Integer is_deleted;

    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * CreateData :用于自动创建时间
     * JsonFormat ： 格式化 能让前后端能够读取到
     */
    @CreatedDate
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * cascade：级联操作
     * CascadeType.MERGE 级联合并（级联更新）
     * CascadeType.REMOVE 级联删除
     * 指A类新增或者变化，会级联B对象（新增或者变化）
     * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。
     */
    @ManyToMany(cascade={CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "article_label",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labelList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "article_section",
                joinColumns = @JoinColumn(name = "article_id"),
                inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private Section section;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "article_comment",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private Set<Comment> commentList;




}
