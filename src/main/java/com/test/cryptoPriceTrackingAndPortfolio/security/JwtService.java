package com.test.cryptoPriceTrackingAndPortfolio.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtService {

    @Value("${jwt-key}")
    private String SECRET;

    @Value("${expire-time}")
    private Long EXPIRE_TIME;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractClaims(token).getSubject();
        Date expirationDate = extractClaims(token).getExpiration();

        return userDetails.getUsername().equals(username) && expirationDate.after(new Date());
    }

    public Claims extractClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
