package com.kabu.bif_eventdemo.controller;

import com.kabu.bif_eventdemo.pojo.Category;
import com.kabu.bif_eventdemo.pojo.Result;
import com.kabu.bif_eventdemo.service.CategoryService;
import com.kabu.bif_eventdemo.service.UserService;
import com.kabu.bif_eventdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categorylist")
public Result <List<Category>>categoryList(Integer id){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Category> categories = categoryService.categoryList(userId);
        return Result.success(categories);
    }
    @PostMapping("/categoryadd")
    public Result categoryAdd(@RequestBody Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer usernameid = (Integer) map.get("id");
        category.setCreateUser(usernameid);
        categoryService.categoryAdd(category);
        return Result.success();
    }
    @PutMapping("/categoryupdate")
    public Result categoryUpdate(@RequestBody Category category){
        category.setUpdateTime(LocalDateTime.now());
        categoryService.categoryUpdate(category);
        return Result.success();
    }
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id){
        Map<String,Object> map=categoryService.detail(id);
        return Result.success(map);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
