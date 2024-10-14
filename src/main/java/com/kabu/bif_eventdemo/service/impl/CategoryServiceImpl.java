package com.kabu.bif_eventdemo.service.impl;

import com.kabu.bif_eventdemo.mapper.CategoryMapper;
import com.kabu.bif_eventdemo.pojo.Category;
import com.kabu.bif_eventdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> categoryList(Integer id) {

        return categoryMapper.categroyList( id);
    }

    @Override
    public void categoryAdd(Category category) {
        categoryMapper.categroyAdd(category);
    }

    @Override
    public void categoryUpdate(Category category) {
        categoryMapper.categoryUpdate(category);
    }

    @Override
    public Map<String, Object> detail(Integer id) {
        return categoryMapper.detail(id);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
