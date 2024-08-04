package com.thm.gw.controllers.admin;

import com.thm.gw.dtos.user.ClientDTO;
import com.thm.gw.dtos.user.ManagerDTO;
import com.thm.gw.dtos.user.OwnerDTO;
import com.thm.gw.dtos.user.UserDTO;
import com.thm.gw.forms.auth.ClientRegisterForm;
import com.thm.gw.forms.auth.ManagerRegisterForm;
import com.thm.gw.forms.auth.OwnerRegisterForm;
import com.thm.gw.forms.user.ClientUpdateForm;
import com.thm.gw.forms.user.ManagerUpdateForm;
import com.thm.gw.forms.user.OwnerUpdateForm;
import com.thm.gw.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(params = "email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        return ResponseEntity.ok(userService.getAllOwners());
    }

    @GetMapping("/managers")
    public ResponseEntity<List<ManagerDTO>> getAllManagers() {
        return ResponseEntity.ok(userService.getAllManagers());
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(userService.getAllClients());
    }

    @PostMapping("/owners")
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody @Valid OwnerRegisterForm form) {
        return ResponseEntity.ok(userService.addOwner(form));
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerDTO> addManager(@RequestBody @Valid ManagerRegisterForm form) {
        return ResponseEntity.ok(userService.addManager(form));
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> addClient(@RequestBody @Valid ClientRegisterForm form) {
        return ResponseEntity.ok(userService.addClient(form));
    }

    @PutMapping("/owners/{id:^[0-9]+$}")
    public ResponseEntity<OwnerDTO> updateOwner(
            @PathVariable Long id,
            @RequestBody @Valid OwnerUpdateForm form
    ) {
        return ResponseEntity.ok(userService.updateOwner(id, form));
    }

    @PutMapping("/managers/{id:^[0-9]+$}")
    public ResponseEntity<ManagerDTO> updateManager(
            @PathVariable Long id,
            @RequestBody @Valid ManagerUpdateForm form
    ) {
        return ResponseEntity.ok(userService.updateManager(id, form));
    }

    @PutMapping("/clients/{id:^[0-9]+$}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable Long id,
            @RequestBody @Valid ClientUpdateForm form
    ) {
        return ResponseEntity.ok(userService.updateClient(id, form));
    }

    @PatchMapping("/{id:^[0-9]+$}/lock")
    public ResponseEntity<UserDTO> lockUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerLock(id, true));
    }

    @PatchMapping("/{id:^[0-9]+$}/unlock")
    public ResponseEntity<UserDTO> unlockUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerLock(id, false));
    }

    @PatchMapping("/{id:^[0-9]+$}/disable")
    public ResponseEntity<UserDTO> disableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerEnable(id, false));
    }

    @PatchMapping("/{id:^[0-9]+$}/enable")
    public ResponseEntity<UserDTO> enableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerEnable(id, true));
    }
}
