package com.app.jiralite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final SecretKey key;
    private final String issuer;
    private final long accessTtlMinutes;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.issuer}") String issuer,
            @Value("${app.jwt.accessTtlMinutes}") long accessTtlMinutes
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.accessTtlMinutes = accessTtlMinutes;
    }

    public String createAccessToken(Long userId, Long activeWorkspaceId) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(accessTtlMinutes * 60);

        return Jwts.builder()
                .issuer(issuer)
                .subject(String.valueOf(userId))
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claims(Map.of("workspaceId", activeWorkspaceId))
                .signWith(key)
                .compact();
    }

    public Long parseUserId(String token) {
        String sub = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(sub);
    }

    public Long parseWorkspaceId(String token) {
        Object ws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("workspaceId");
        return ws == null ? null : Long.parseLong(ws.toString());
    }
}
