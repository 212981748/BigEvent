package com.kabu.bif_eventdemo.service;

import com.kabu.bif_eventdemo.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
     List<Category> categoryList(Integer id);

     void categoryAdd(Category category);

     void categoryUpdate(Category category);

     Map<String, Object> detail(Integer id);

     void delete(Integer id);
}
