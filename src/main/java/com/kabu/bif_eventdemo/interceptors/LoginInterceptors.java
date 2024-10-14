package com.kabu.bif_eventdemo.interceptors;

import com.kabu.bif_eventdemo.utils.JwtUnitl;
import com.kabu.bif_eventdemo.utils.ThreadLocalUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Autowired
  private   StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        //浏览器携带的令牌

        System.out.println("token = " + token);
        try {


//Redis中的令牌
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            String os = ops.get(token);
            if (os==null){
                throw new RuntimeException();
            }

            Map<String,Object> body = (Map<String, Object>) Jwts.parser().setSigningKey(JwtUnitl.Key)
                    .parse(token)
                    .getBody();
            System.out.println("body = " + body);
            ThreadLocalUtil.set(body);
            //ThreadLocalUtil.set(parse);
            return true;
        } catch (ExpiredJwtException e) {
            response.setStatus(401);
           return  false;
        }

    }
}
