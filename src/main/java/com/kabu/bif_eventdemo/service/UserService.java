package com.kabu.bif_eventdemo.service;

import com.kabu.bif_eventdemo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserService {

   void update(User user);

    //查询用户是否被占用
    User findByUserName(String username);
    // 注册用户
    void register(String username, String password);

    void updateAcatar( String avatarUrl,  Integer id);

 void updatePwd(String newpassword,Integer id);
}
