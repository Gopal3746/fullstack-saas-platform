package com.app.jiralite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class WorkspaceDtos {
  public static class CreateWorkspaceRequest {
    @NotBlank public String name;
  }

  public static class AddMemberRequest {
    @Email @NotBlank public String email;
    @NotBlank public String role; // ADMIN or MEMBER
  }
}
