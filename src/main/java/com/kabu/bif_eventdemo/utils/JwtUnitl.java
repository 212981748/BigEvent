package com.kabu.bif_eventdemo.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUnitl {
    public static final String Key="hahaha";
    public static String  genToken(Map<String, Object> claims) {
        JwtBuilder jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Key)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12));
        String token =jwt.compact();
        return token;
    }

    public Map <String,Object>par(String token ){
            return  Jwts.parser()
                        .setSigningKey(Key)

                        .parseClaimsJws(token)
                        .getBody();

    }
}
