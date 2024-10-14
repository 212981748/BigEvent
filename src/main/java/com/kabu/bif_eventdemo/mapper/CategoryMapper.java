package com.kabu.bif_eventdemo.mapper;

import com.kabu.bif_eventdemo.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {
    @Select("select * from category where create_user=#{cateUser}")
    List<Category> categroyList(Integer id);
@Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) VALUES (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void categroyAdd(Category category);
@Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void categoryUpdate(Category category);
@Select("select category_name,category_alias,create_time,update_time from category where id=#{id}")
    Map<String, Object> detail(Integer id);
@Delete("delete from category where id=#{id}")
    void delete(Integer id);
}

