package com.kabu.bif_eventdemo.mapper;

import com.kabu.bif_eventdemo.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface ArticleMapper {
    @Insert("insert into article ( title, content, cover_img, state, category_id, create_user, create_time, update_time)values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void articleAdd(Article article);
@Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},create_user=#{createUser},update_time=now(),create_time=now() where id=#{id}")
    void articleupdate(Article article);
@Select( "select * from article where id=#{id}")
    Article detail(Integer id);
@Delete("delete  from article where id=#{id}")
    void delete(Integer id);

    List<Article> findlist( @Param("userId") Integer userId,@Param("categoryId") Integer categoryId, @Param("state") String state);
}
