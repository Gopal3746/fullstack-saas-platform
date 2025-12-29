package com.app.jiralite.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser implements UserDetails {

  private final Long userId;
  private final String email;
  private final Long workspaceId;
  private final Collection<? extends GrantedAuthority> authorities;

  public AuthUser(
      Long userId,
      String email,
      Long workspaceId,
      Collection<? extends GrantedAuthority> authorities) {
    this.userId = userId;
    this.email = email;
    this.workspaceId = workspaceId;
    this.authorities = authorities;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getWorkspaceId() {
    return workspaceId;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
