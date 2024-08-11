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

    /**
     * Generates jwt token using username
     *
     * @param username username of the logged user
     * @return generated jwt token
     */
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

    /**
     * Validates the token
     *
     * @param token       token of logged user
     * @param userDetails logged user
     * @return true or false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractClaims(token).getSubject();
        Date expirationDate = extractClaims(token).getExpiration();

        return userDetails.getUsername().equals(username) && expirationDate.after(new Date());
    }

    /**
     * It returns claims of token
     *
     * @param token token of logged user
     * @return Claims
     */
    public Claims extractClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
