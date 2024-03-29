package com.jjlee.oauth.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class SimpleJwtService implements JwtService{
    private static String SECRET = "가나다라마바사아자차카타파하에헤으에으에으에으에으에으에으에으에으어허허허";

    @PostConstruct
    protected void init() {
        SECRET = Base64.getEncoder()
            .encodeToString(SECRET.getBytes(StandardCharsets.UTF_8));
    }
    @Override
    public String issuedToken(String subject, String role, long periodSecond) {
        final Claims claims = Jwts.claims().setSubject(subject);
        claims.put("role", role);

        final Date now = new Date();
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + periodSecond * 1000))
            .signWith(getSigningKey())
            .compact();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            final Claims claims = getBody(token);
            return claims.getExpiration().after(new Date());
        } catch (final Exception e) {
            return false;
        }
    }

    @Override
    public String getSubject(String token) {
        final Claims claims = getBody(token);

        return claims.getSubject();
    }

    private Key getSigningKey() {
        final byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
