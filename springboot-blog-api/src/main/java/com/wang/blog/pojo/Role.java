package com.wang.blog.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 9:45
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String name_zh;


//    /**
//     * cascade：级联操作
//     * CascadeType.MERGE 级联合并（级联更新）
//     * CascadeType.REMOVE 级联删除
//     * 指A类新增或者变化，会级联B对象（新增或者变化）
//     * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。
//     */
//    @OneToMany(cascade={CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
//    @JoinTable(name = "role_permission",
//                joinColumns = @JoinColumn(name = "role_id"),
//                inverseJoinColumns = @JoinColumn(name = "permission_id")
//
//    )
//    private List<Permission> permissionList;

    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JoinTable(name = "role_menu",
                joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    //这种方法就是在不修改原结构注释的情况下，可以修改一下抓取策略，分开select实体，这样就不会出现重复列现象
    @Fetch(FetchMode.SUBSELECT)
    private List<Menu> menuList;
}
