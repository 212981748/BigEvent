package com.kabu.bif_eventdemo.controller;

import com.kabu.bif_eventdemo.pojo.Article;
import com.kabu.bif_eventdemo.pojo.PageBean;
import com.kabu.bif_eventdemo.pojo.Result;
import com.kabu.bif_eventdemo.service.ArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
  @PostMapping("/articleadd")
    public Result articleAdd(@RequestBody Article article){
      articleService.articleAdd(article);
      return  Result.success();
  }
  @PutMapping("/articleupdate")
    private Result articleupdate(@RequestBody Article article){
      articleService.articleupdate(article);
      return Result.success(article);
  }
  @GetMapping("/detail")
  public Result detail(@RequestParam Integer id){
      Article article=articleService.detail(id);
      return Result.success(article);
  }
  @DeleteMapping("/delete")
  public Result delete(@RequestParam Integer id){
      articleService.delete(id);
      return Result.success();
  }
@GetMapping("/findlist")
    public Result<PageBean<Article>>list(Integer pageNum,
                                         Integer pageSize,
                                        @RequestParam(required = false) Integer categoryId,
                                         @RequestParam(required = false) String state){

     PageBean<Article> pb= articleService.findlist(pageNum,pageSize,categoryId,state);
     return Result.success(pb);
}
}
