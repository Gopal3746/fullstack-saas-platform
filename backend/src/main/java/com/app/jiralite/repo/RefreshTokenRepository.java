package com.app.jiralite.repo;

import com.app.jiralite.model.RefreshToken;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByTokenHash(String tokenHash);

  void deleteByExpiresAtBefore(Instant cutoff);
}
