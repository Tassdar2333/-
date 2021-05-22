package com.wang.blog.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 14:10
 */
@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //评论号
    private Integer id;
    //评论类型(评论-评论文章 或 回复-回复他人的评论)
    private String type;
    //评论内容
    private String content;
    //评论人的id
    private Integer from_uid;
    //评论目标人的id 如果没有目标人则该字段为空
    private Integer to_uid;

    //评论/回复创建时间
    @CreatedDate
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @Column(name = "create_time")
    private Date createTime;


    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name="from_uid",referencedColumnName = "user_id",insertable=false, updatable=false)
    @JsonBackReference
    private User fromUser;

    @JoinColumn(name="to_uid",referencedColumnName = "user_id",insertable=false, updatable=false)
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private User toUser;
}
