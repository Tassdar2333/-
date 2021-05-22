package com.wang.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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
 * @Date 2021/3/31 9:40
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "notice")
//开启监听用于创建时间自动写入
@EntityListeners(AuditingEntityListener.class)
public class Notice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    /**
     * 公告创建时间
     * @CreateDate 自动设置创建时间
     * @JsonFormat 转换时间格式
     */
    @CreatedDate
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @Column(name = "createtime")
    private Date createTime;
}
