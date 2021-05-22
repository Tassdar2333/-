package com.wang.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/22 13:26
 * 用户
 */
@Data //lombok 省略set get方法
@NoArgsConstructor //空构造
@Accessors(chain = true) // Lombok链式编程
@Entity //标识映射类
@Table(name = "user") //标识映射表
//用于监听并自动添加创建时间
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "articleList")//这里我在模糊查询时 出现了无限递归调用的问题
public class User implements Serializable {
    //用户id
    /**
     * @Id 映射主键
     * @Column()映射字段
     * a，TABLE：使用一个特定的数据库表格来保存主键。
     * b，SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * c，IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * d，AUTO：主键由程序控制。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer user_id;
    //用户名 账户
    @Column(name="username")
    private String username;
    //密码
    @Column(name="password")
    private String password;
    //电话
    @Column(name="phone")
    private String phone;
    //邮箱
    @Column(name="email")
    private String email;
    //密码盐
    @Column(name = "salt")
    private String salt;
    /**
     * 创建时间
     * @CreateData用于自动设置创建时间
     * @JsonFormat用于转化时间格式防止 前端显示错误
     */
    @Column(name="createtime")
    @CreatedDate
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    private Date createTime;
    //是否被删除
    @Column(name="is_delete")
    private Integer is_delete;
    //权限
    @Column(name="perms")
    private String perms;

    @Column(name = "desc")
    private String desc;

    /**
     * cascade：级联操作
     * CascadeType.MERGE 级联合并（级联更新）
     * 指A类新增或者变化，会级联B对象（新增或者变化）
     * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。
     */
    @OneToMany(cascade={CascadeType.ALL},orphanRemoval=true,fetch = FetchType.EAGER)
    @JoinTable(name = "user_article",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articleList;

    @ManyToOne
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role;



}
