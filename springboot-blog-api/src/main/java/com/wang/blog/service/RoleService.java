package com.wang.blog.service;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:14
 * 角色业务接口
 */
public interface RoleService {
    List<RoleService> queryAll();

    void updateRole();
}
