package com.wang.blog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyArticlePage;
import com.wang.blog.page.MyUserPage;
import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.User;
import com.wang.blog.pojo.User_Article;
import com.wang.blog.repository.UserRepository;
import com.wang.blog.repository.User_ArticleRepository;
import com.wang.blog.repository.User_RoleRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/3/31 15:19
 */
@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    User_ArticleRepository user_articleRepository;

    @Autowired
    User_RoleRepository user_roleRepository;

    /**
     * 用于获取用户列表 同时将所有的密码隐藏
     * @return
     */
    @Override
    public List<User> queryAll() {
        //这里会有问题 会返回密码
        List<User> userList = userRepository.findAll();
        for(User user:userList){
            user.setPassword("*");
        }
        return userList;
    }




    /**
     * 通过用户名查询 用户信息
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
       return userRepository.findByUsername(username);
    }



    /**
     * 通过用户名删除用户
     * @param usrName
     * @return
     */
    @Override
    public Integer deleteByUsername(String usrName) {
        return null;
    }

    /**
     * 注册用户
     * @param uer
     * @return
     */
    @Override
    public Integer register(User uer) {
        System.out.println(uer);
        if(findByUserName(uer.getUsername()) != null){
            System.out.println("已存在");
            return 0;
        }else{
            userRepository.saveUser(uer);
            User dataUser = userRepository.findByUsername(uer.getUsername());
            user_roleRepository.registerAdd(dataUser.getUser_id(),2);
            return 1;
        }

    }

    /**
     * 用于登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(token);
        User dataUser = findByUserName(user.getUsername());
        if(dataUser != null){
            if(dataUser.getPassword().equals(user.getPassword())){

                return dataUser;
            }else{
                return new User().setUsername("密码错误");
            }
        }else{
            return new User().setUsername("用户不存在");
        }
    }

    /**
     * 更新用户信息
     * @return
     */
    public User update(String json){
        //先转化
        JSONObject object = JSONObject.parseObject(json);
        //从json数据中获取对象
        User user = JSON.toJavaObject(object.getJSONObject("user"),User.class);
        //修改保存
        userRepository.updateUser(user);
        System.out.println(userRepository.findById(user.getUser_id()).orElse(null));
        return userRepository.findById(user.getUser_id()).orElse(null);
    }

    @Override
    public String getUserName(Integer id) {
        return userRepository.findById(id).orElse(null).getUsername();
    }


    /**
     * 这里是获取文章的作者名
     *
     * @param id
     * @return
     */
    @Override
    public String getNameByArticleId(Integer id) {
        User_Article ua = user_articleRepository.findUser_ArticleByArticle_id(id);
        return userRepository.findById(ua.getUser_id()).orElse(null).getUsername();
    }

    @Override
    public void forgetUpdate(String username, String newPassword) {
        userRepository.forgetUpdate(username,newPassword);
    }

    /**
     * 获取当前一页的所有用户
     * @param currentPage
     * @return
     */
    @Override
    public MyUserPage getAllUserCurrentPage(Integer currentPage){
        List<Sort.Order> orders = new ArrayList<>();
        //通过createTime 倒序查询
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        //排序对象
        Sort sort = Sort.by(orders);
        //分页
        Page<User> pages = userRepository.findAll(PageRequest.of(currentPage,10,sort));
        //自定义page对象
        MyUserPage myPage = new MyUserPage();
        myPage.setContentList(pages.getContent());
        myPage.setTotalPageSize(pages.getTotalPages());
        return myPage;
    }

    @Override
    public String updatePassword(String json) {
        JSONObject n = JSONObject.parseObject(json);
        Integer id = n.getInteger("id");
        String old = n.getString("old");
        String newPwd = n.getString("new");
        User user = userRepository.findById(id).orElse(null);
        if(user.getPassword() == old){
            forgetUpdate(user.getUsername(),newPwd);
            return "200";
        }else{
            System.out.println("密码不对");
            return "400";
        }

    }

}
