package com.thm.gw.controllers.admin;

import com.thm.gw.dtos.role.RoleDTO;
import com.thm.gw.forms.role.RoleForm;
import com.thm.gw.services.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRoleController {

    private final IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping(params = "name")
    public ResponseEntity<RoleDTO> getRoleByName(@RequestParam String name) {
        return ResponseEntity.ok(roleService.getByName(name));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> addRole(@Valid @RequestBody RoleForm form) {
        return ResponseEntity.ok(roleService.add(form));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @Valid @RequestBody RoleForm form) {
        return ResponseEntity.ok(roleService.update(id, form));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleDTO> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.delete(id));
    }
}
