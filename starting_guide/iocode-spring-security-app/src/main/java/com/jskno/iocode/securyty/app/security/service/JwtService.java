package com.jskno.iocode.securyty.app.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateJwtToken(String username) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + (1000 * 24 * 24));
        return Jwts.builder()
            .header().type("JWT")
            .and()
            .subject(username)
            .issuedAt(issuedAt)
            .expiration(expirationDate)
            .signWith(generateSigningKey())
            .compact();
    }

    public Claims extractClaims(String jwtToken) {
        return Jwts.parser()
            .verifyWith(generateSigningKey())
            .build()
            .parseSignedClaims(jwtToken)
            .getPayload();
    }

    private SecretKey generateSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String extractSubject(String jwtToken) {
        return extractClaims(jwtToken).getSubject();
    }

    public Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken).getExpiration();
    }

    public boolean isTokenValid(String jwtToken) {
        return new Date().before(extractExpiration(jwtToken));
    }

}
