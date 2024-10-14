package com.kabu.bif_eventdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testRedis(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("username","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
    @Test
    public  void  getRedis(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String username = stringStringValueOperations.get("username");
        System.out.println("username = " + username);
        Boolean hasKey = stringRedisTemplate.hasKey("username");
        System.out.println("hasKey = " + hasKey);
        DataType type = stringRedisTemplate.type("username");
        System.out.println("type = " + type);
        Boolean d = stringRedisTemplate.delete("username");
        System.out.println("d = " + d);
    }
   /* @Test
    public void del(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String username = ops.get("username");
        System.out.println("username = " + username);
        Boolean del = stringRedisTemplate.delete("username");
        System.out.println("del = " + del);
    }*/
}
