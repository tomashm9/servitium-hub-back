package com.thm.gw.controllers.auth;

import com.thm.gw.dtos.auth.UserTokenDTO;
import com.thm.gw.forms.auth.ClientRegisterForm;
import com.thm.gw.forms.auth.LoginForm;
import com.thm.gw.forms.auth.OwnerRegisterForm;
import com.thm.gw.services.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final IAuthService authService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody @Valid LoginForm form) {
        return ResponseEntity.ok(authService.login(form));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup/owners")
    public ResponseEntity<UserTokenDTO> registerOwner(@RequestBody @Valid OwnerRegisterForm form) {
        return ResponseEntity.ok(authService.register(form));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup/clients")
    public ResponseEntity<UserTokenDTO> registerClient(@RequestBody @Valid ClientRegisterForm form) {
        return ResponseEntity.ok(authService.register(form));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup/managers")
    public ResponseEntity<UserTokenDTO> registerManager(@RequestBody @Valid ClientRegisterForm form) {
        return ResponseEntity.ok(authService.register(form));
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/invite/manager")
    public ResponseEntity<Void> inviteManager(@RequestBody @Valid String email) {
        authService.inviteManager(email);
        return ResponseEntity.ok().build();
    }
}
