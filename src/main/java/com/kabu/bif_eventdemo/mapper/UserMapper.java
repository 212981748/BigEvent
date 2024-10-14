package com.kabu.bif_eventdemo.mapper;

import com.kabu.bif_eventdemo.pojo.User;
import com.kabu.bif_eventdemo.utils.Md5Util;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

@Mapper
public interface UserMapper {
    //查询
    @Select("select * from user where username=#{username}")
    User findByUserName( String username);
    //添加
    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void add(@Param("username") String username,@Param("password") String password);
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id} ")
    void update(User user);
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id} ")
    void updateAvatar(@Param("avatarUrl") String avatarUrl,@Param("id") Integer id);
    @Update("update user set password=#{md5String},update_time=now() where id=#{id} ")
    void updatePwd(@Param("md5String") String md5String,@Param("id") Integer id);



}
