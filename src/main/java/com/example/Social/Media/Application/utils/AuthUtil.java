package com.example.Social.Media.Application.utils;

import com.example.Social.Media.Application.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {
    @Value("${jwt.securityKey}")
    private String JwtSecurityKey;

    private SecretKey getSecurityKey() {
        return Keys.hmacShaKeyFor(JwtSecurityKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getUserId().toString())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecurityKey())
                .compact();
    }
}
