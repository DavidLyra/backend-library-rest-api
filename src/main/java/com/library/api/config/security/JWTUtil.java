package com.library.api.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${lampiris.jwt.expiration}")
    private String expirationTimeInSeconds;

    @Value("${lampiris.jwt.secret}")
    private String key;

    public String getUsernameFromToken(String token) {
        return this.getAllClaimsFromToken(token)
                   .getSubject();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(this.key)
                   .parseClaimsJws(token)
                   .getBody();
    }

    public String generateToken(UserDetails user) {
        return this.doGenerateToken(this.generateDefaultClaims(user), user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        long expirationTimeLong = Long.parseLong(this.expirationTimeInSeconds);

        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000L);

        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(username)
                   .setIssuedAt(createdDate)
                   .setExpiration(expirationDate)
                   .signWith(SignatureAlgorithm.HS256, this.key)
                   .compact();
    }

    private Map<String, Object> generateDefaultClaims(UserDetails user) {
        Map<String, Object> claims = new HashMap();
        claims.put("roles", user.getAuthorities());
        return claims;
    }

    public Boolean isValidToken(String token) {
        return !this.isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return this.getAllClaimsFromToken(token)
                   .getExpiration();
    }
}
