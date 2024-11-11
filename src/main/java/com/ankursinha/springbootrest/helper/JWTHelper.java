package com.ankursinha.springbootrest.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JWTHelper {
    private final String SECRET_KEY = "cr666N7wIV3KJ2xOQpWtbfAekL4YXd9gbnJMs8SJ9sF4";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    try {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    } catch (io.jsonwebtoken.SignatureException | io.jsonwebtoken.MalformedJwtException | io.jsonwebtoken.ExpiredJwtException e) {
        // Log the specific issue for better diagnostics
        System.err.println("Token parsing issue: " + e.getMessage());
        throw new RuntimeException("Invalid Token");
    }
}

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Set the issued time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Sign with the secret key
                .compact();
    }

    public boolean validateToken(String token, String email) {
//        final String extractedUsername = extractUsername(token);
//        return (extractedUsername.equals(email) && !isTokenExpired(token));
// Check if the token is valid
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());  // Check if token has expired
    }
}

