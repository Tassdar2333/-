package com.wang.blog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wang.blog.page.MyArticlePage;
import com.wang.blog.page.MyNoticePage;
import com.wang.blog.pojo.Article;
import com.wang.blog.pojo.Label;
import com.wang.blog.pojo.Notice;
import com.wang.blog.pojo.User;
import com.wang.blog.repository.ArticleRepository;
import com.wang.blog.repository.UserRepository;
import com.wang.blog.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date 2021/4/8 13:18
 */
@Service
public class ArticleServiceImp implements ArticleService{
    Logger logger = LoggerFactory.getLogger(ArticleServiceImp.class);
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Article> queryAll() {
        return articleRepository.findAll();
    }

    @Override
    public void updateArticleById(Integer id) {

    }

    /**
     * 文章删除
     * @param json
     * @param id
     * @param username
     * @return
     */
    @Override
    public MyArticlePage delete(String json,Integer id,String username) {
        JSONObject n = JSONObject.parseObject(json);
        if(id == 0){
            Article article = JSON.toJavaObject(n.getJSONObject("articleList"), Article.class);
            System.out.println(article.getCommentList());
            articleRepository.deleteById(article.getId());
        }else if(id == 1){
            List<Article> articleList = JSON.toJavaObject(n.getJSONObject("articleList"),List.class);
            //获取批量删除id数组
            List<Integer> ids = new ArrayList<>();
            for(Object jsonObject : articleList){
                Article article = JSONObject.parseObject(jsonObject.toString(),Article.class);
                ids.add(article.getId());
            }
            articleRepository.deleteArticleByIdIn(ids);
        }
        if(username != null){
            return userCurrentArticleListPage(1,username);
        }else{
            return commonArticlePage(0);
        }


    }

    /**
     *文章发布服务
     * @param json
     * @return
     */
    @Override
    public MyArticlePage postArticle(String json) {
        //通过前端传过来的json串 获取json对象
        JSONObject n = JSONObject.parseObject(json);
        //获取用户名
        String username = n.getString("username");

        //获取Article对象
        Article article = JSON.toJavaObject(n.getJSONObject("article"),Article.class);

        //通过用户名获取用户信息
        User user = userRepository.findByUsername(username);
        System.out.println("user:" + user);
        /**
         * 这里如果一开始是没有article的 所以这里会判断为null
         */
        List<Article> articleList;
        if(user.getArticleList() == null){
            articleList = new ArrayList<>();
        }else{
            articleList = user.getArticleList();
        }
        //添加文章
        articleList.add(article);
        user.setArticleList(articleList);
        userRepository.save(user);
        return commonArticlePage(0);
    }

    /**
     * 用于查询公共信息页面文章列表数据
     * @param currentPage
     * @return
     */
    public MyArticlePage commonArticlePage(Integer currentPage){
        List<Sort.Order> orders = new ArrayList<>();
        //通过createTime 倒序查询
        orders.add(new Sort.Order(Sort.Direction.DESC,"createTime"));
        //排序对象
        Sort sort = Sort.by(orders);
        //分页
        Page<Article> pages = articleRepository.findAll(PageRequest.of(currentPage,10,sort));
        //自定义page对象
        MyArticlePage myPage = new MyArticlePage();
        myPage.setContentList(pages.getContent());
        myPage.setTotalPageSize(pages.getTotalPages());
        return myPage;

    }

    /**
     * 查询用户文章信息
     * @param id
     * @return
     */
    @Override
    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }


    /**
     * 用于用户 自己管理文章时获取列表
     * @param currentPage
     * @param username
     * @return
     */
    public MyArticlePage userCurrentArticleListPage(Integer currentPage,String username){
        User user = userRepository.findByUsername(username);
        MyArticlePage myArticlePage = PageUtil.startPage(user.getArticleList(),currentPage,5);
        return myArticlePage;
    }

    /**
     * 模糊查询 搜索功能
     * @param name
     * @param currentPage
     * @return
     */
    @Override
    public MyArticlePage findByArticleNameLike(String name,Integer currentPage) {
        MyArticlePage myArticlePage = PageUtil.startPage(articleRepository.findByTitleLike("%"+name+"%"),currentPage,5);
        System.out.println(myArticlePage);
        return myArticlePage;

    }

    @Override
    public void viewCount(Integer id){
        if (id != null) {
            articleRepository.updateView_count(articleRepository.findById(id).orElse(null).getViewCount() + 1,id);
        }


    }

    @Override
    public List<Article> getPopularArticle() {
        List<Sort.Order> orders = new ArrayList<>();
        //通过view_count 倒序查询
        orders.add(new Sort.Order(Sort.Direction.DESC,"viewCount"));
        //排序对象
        Sort sort = Sort.by(orders);
        //分页
//        Page<Article> pages = articleRepository.findAll(PageRequest.of(0,10,sort));
        Page<Article> pages = articleRepository.findAll(PageRequest.of(0,10,sort));
        return pages.getContent();
    }


}
