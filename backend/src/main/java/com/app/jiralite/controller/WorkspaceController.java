package com.app.jiralite.controller;

import com.app.jiralite.dto.WorkspaceDtos;
import com.app.jiralite.model.Role;
import com.app.jiralite.model.User;
import com.app.jiralite.model.Workspace;
import com.app.jiralite.model.WorkspaceMember;
import com.app.jiralite.repo.UserRepository;
import com.app.jiralite.repo.WorkspaceMemberRepository;
import com.app.jiralite.repo.WorkspaceRepository;
import com.app.jiralite.security.SecurityUtil;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

  private final WorkspaceRepository wsRepo;
  private final WorkspaceMemberRepository memberRepo;
  private final UserRepository userRepo;

  public WorkspaceController(
      WorkspaceRepository wsRepo, WorkspaceMemberRepository memberRepo, UserRepository userRepo) {
    this.wsRepo = wsRepo;
    this.memberRepo = memberRepo;
    this.userRepo = userRepo;
  }

  @GetMapping
  public List<Map<String, Object>> myWorkspaces() {
    var u = SecurityUtil.current();
    var memberships = memberRepo.findByUserId(u.getUserId());
    return memberships.stream()
        .map(
            m ->
                Map.<String, Object>of(
                    "workspaceId", m.getWorkspace().getId(),
                    "name", m.getWorkspace().getName(),
                    "role", m.getRole().name()))
        .toList();
  }

  @PostMapping
  public Map<String, Object> create(@Valid @RequestBody WorkspaceDtos.CreateWorkspaceRequest req) {
    var u = SecurityUtil.current();

    Workspace ws = new Workspace();
    ws.setName(req.name);
    ws = wsRepo.save(ws);

    WorkspaceMember m = new WorkspaceMember();
    m.setWorkspace(ws);
    m.setUser(userRepo.findById(u.getUserId()).orElseThrow());
    m.setRole(Role.ADMIN);
    memberRepo.save(m);

    return Map.of("workspaceId", ws.getId(), "name", ws.getName());
  }

  @PostMapping("/{workspaceId}/members")
  @PreAuthorize("hasRole('ADMIN')")
  public Map<String, String> addMember(
      @PathVariable Long workspaceId, @Valid @RequestBody WorkspaceDtos.AddMemberRequest req) {
    var u = SecurityUtil.current();

    // ensure caller is admin IN THIS workspace
    memberRepo
        .findByWorkspaceIdAndUserId(workspaceId, u.getUserId())
        .filter(m -> m.getRole() == Role.ADMIN)
        .orElseThrow(() -> new IllegalArgumentException("Not allowed"));

    User user =
        userRepo
            .findByEmail(req.email.toLowerCase())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Workspace ws = wsRepo.findById(workspaceId).orElseThrow();

    WorkspaceMember m = new WorkspaceMember();
    m.setWorkspace(ws);
    m.setUser(user);
    m.setRole(Role.valueOf(req.role));
    memberRepo.save(m);

    return Map.of("status", "added");
  }
}
