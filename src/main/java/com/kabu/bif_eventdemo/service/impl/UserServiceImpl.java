package com.kabu.bif_eventdemo.service.impl;

import com.kabu.bif_eventdemo.mapper.UserMapper;
import com.kabu.bif_eventdemo.pojo.User;
import com.kabu.bif_eventdemo.service.UserService;
import com.kabu.bif_eventdemo.utils.Md5Util;
import com.kabu.bif_eventdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public User findByUserName(String username) {
      User u=   userMapper.findByUserName(username);
         return u;
    }

    @Override
    public void register(String username, String password) {
       //加密处理
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,md5String);
    }



    @Override
    public void updateAcatar(String avatarUrl,Integer id) {
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newpassword,Integer id) {
        System.out.println("修改密码方法被执行");
        String md5String = Md5Util.getMD5String(newpassword);
        userMapper.updatePwd(md5String,id);
    }


}
