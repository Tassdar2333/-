package com.wang.blog.service;

import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 10:16
 * 权限业务接口
 */
public interface PermissionService {

    List<PermissionService> queryAll();

    List<PermissionService> queryByRole(Integer roleId);
}
