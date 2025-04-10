package com.bqt.flight_booking_server.configuration;


import com.bqt.flight_booking_server.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;


    //Tao JWT
    public String generateToken(User user) {
        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(user.getEmail()) // email
                .claim("roles", user.getRole().name()) // luu role
                .claim("fullname", user.getFullname())
                .setIssuedAt(now) // time tao token
                .setExpiration(expiryDate) // time het han
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    // Lấy email từ JWT
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // lay fullname tu JWT
    public String getFullnameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("fullname", String.class);
    }

    // Kiểm tra JWT hợp lệ
    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
