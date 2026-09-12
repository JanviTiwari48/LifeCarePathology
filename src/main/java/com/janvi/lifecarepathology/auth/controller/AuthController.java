package com.janvi.lifecarepathology.auth.controller;

import com.janvi.lifecarepathology.auth.dto.LoginRequest;
import com.janvi.lifecarepathology.auth.dto.LoginResponse;
import com.janvi.lifecarepathology.config.jwt.JwtUtil;
import com.janvi.lifecarepathology.config.security.UserPrincipal;
import com.janvi.lifecarepathology.user.dto.UserRequest;
import com.janvi.lifecarepathology.user.dto.UserResponse;
import com.janvi.lifecarepathology.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            String token = jwtUtil.generateToken(principal.getUsername());
            String role = principal.getUser().getRole().name();

            return ResponseEntity.ok(new LoginResponse(token, principal.getUsername(), role));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}