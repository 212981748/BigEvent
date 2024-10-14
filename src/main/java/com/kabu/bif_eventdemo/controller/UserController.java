package com.kabu.bif_eventdemo.controller;

import com.kabu.bif_eventdemo.utils.JwtUnitl;
import com.kabu.bif_eventdemo.pojo.Result;
import com.kabu.bif_eventdemo.pojo.User;
import com.kabu.bif_eventdemo.service.UserService;
import com.kabu.bif_eventdemo.utils.Md5Util;
import com.kabu.bif_eventdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
   private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/register")
    public Result register( String username,String password){
        User u =userService.findByUserName(username);
        if (u==null){
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户名已占用");
        }
    }


    @PostMapping("/login")
    public Result<String> login(String username,String password) {

        //根据输入的姓名去数据库查询
        User loginUserName = userService.findByUserName(username);
        System.out.println("loginUserName = " + loginUserName);
        if (loginUserName == null) {
            return Result.error("用户名错误！");
        }else if (Md5Util.getMD5String(password).equals(loginUserName.getPassword())){
            Map<String,Object> claims = new HashMap<String,Object>();
            claims.put("id",loginUserName.getId());
            claims.put("username",loginUserName.getUsername());
            String token = JwtUnitl.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误!");
        }



     /*   @GetMapping("/userInfo")
    public User userInfo(@RequestHeader(name = "Authorization")String token){
        Map<String,Object> map= (Map<String, Object>) Jwts.parser().setSigningKey(JwtUnitl.Key)
                .parse(token)
                .getBody();
            String username = (String) map.get("username");
            User user = userService.findByUserName(username);
            return user;
        }*/


    @GetMapping("/userInfo")
    public User userInfo(@RequestHeader(name = "Authorization")String token){
       /* Map<String,Object> map= (Map<String, Object>) Jwts.parser().setSigningKey(JwtUnitl.Key)
                .parse(token)
                .getBody();*/

     Map <String,Object> map =  ThreadLocalUtil.get();
        System.out.println("map = " + map);
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return user;
    }


    @PutMapping("/update")
    public  Result update(@Validated @RequestBody User user){
        userService.update(user);
        return Result.success("修改成功");
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userService.updateAcatar(avatarUrl,id);
        return Result.success("已更新头像");
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> parms,@RequestHeader("Authorization") String token){


        System.out.println("parms +++++++++++++++++++++++++++++++= " + parms);
        String oldpassword = parms.get("old_pwd");
        String newpassword = parms.get("new_pwd");
        String repassword = parms.get("re_pwd");
        //判断用户输入是否为空
        if(oldpassword==null||newpassword==null||repassword==null){
            return Result.error("密码不能为空");
        }
        if (!newpassword.equals(repassword)){
            return Result.error("新密码不一致");
        }
        //判断旧密码与数据库密码是否一致
        Map<String,Object> map = ThreadLocalUtil.get();
        String loginusername = (String) map.get("username");
        //数据库查出的user
        User user = userService.findByUserName(loginusername);
        System.out.println("旧密码"+user.getPassword());
        Integer id = user.getId();
        String  s= Md5Util.getMD5String(oldpassword);
        System.out.println("s = " + s);
        if (user.getPassword().equals(Md5Util.getMD5String(oldpassword))){
            userService.updatePwd( newpassword,id);
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.getOperations().delete(token);
            return Result.success("密码修改成功");
        }else{
            return Result.error("密码不一致");
        }

    }

    }








