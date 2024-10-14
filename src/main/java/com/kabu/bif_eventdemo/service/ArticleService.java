package com.kabu.bif_eventdemo.service;

import com.kabu.bif_eventdemo.pojo.Article;
import com.kabu.bif_eventdemo.pojo.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ArticleService  {
    void articleAdd(Article article);

    void articleupdate(Article article);

    Article detail(Integer id);

    void delete(Integer id);


    PageBean<Article> findlist(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
