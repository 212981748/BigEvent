package com.kabu.bif_eventdemo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kabu.bif_eventdemo.mapper.ArticleMapper;
import com.kabu.bif_eventdemo.pojo.Article;
import com.kabu.bif_eventdemo.pojo.PageBean;
import com.kabu.bif_eventdemo.service.ArticleService;
import com.kabu.bif_eventdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void articleAdd(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.articleAdd(article);
    }

    @Override
    public void articleupdate(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        article.setCreateUser(userid);
        articleMapper.articleupdate(article);
    }

    @Override
    public Article detail(Integer id) {
        return articleMapper.detail(id);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public PageBean<Article> findlist(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

       List<Article> as= articleMapper.findlist( userId,categoryId,state);
        Page<Article> p= (Page<Article>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

}
