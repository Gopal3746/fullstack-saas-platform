package com.app.jiralite.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name="refresh_tokens",
    indexes = {
        @Index(name="idx_refresh_user", columnList="user_id"),
        @Index(name="idx_refresh_expires", columnList="expiresAt")
    }
)
public class RefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable=false, unique=true, length=128)
    private String tokenHash;

    @Column(nullable=false)
    private Instant expiresAt;

    private Instant revokedAt;

    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getTokenHash() { return tokenHash; }
    public Instant getExpiresAt() { return expiresAt; }
    public Instant getRevokedAt() { return revokedAt; }

    public void setUser(User user) { this.user = user; }
    public void setTokenHash(String tokenHash) { this.tokenHash = tokenHash; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public void setRevokedAt(Instant revokedAt) { this.revokedAt = revokedAt; }
}
