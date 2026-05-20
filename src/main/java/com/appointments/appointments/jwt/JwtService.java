package com.appointments.appointments.jwt;

import com.appointments.appointments.appUser.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${JWT_SECRET}")
    private String SECRET;

    public String generateToken(AppUser appUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", appUser.getId());
        claims.put("role", appUser.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(appUser.getEmail())
                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 43200 * 30))
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSingInKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    public Integer extractUserId(String token){
        return extractAllClaims(token).get("userId", Integer.class);
    }

    public String extractRole(String token){
        return extractAllClaims(token).get("role", String.class);
    }



    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
