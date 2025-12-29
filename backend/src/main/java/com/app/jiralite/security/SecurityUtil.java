package com.app.jiralite.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  public static AuthUser current() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) return null;

    Object principal = auth.getPrincipal();
    if (!(principal instanceof AuthUser)) return null;

    return (AuthUser) principal;
  }
}
