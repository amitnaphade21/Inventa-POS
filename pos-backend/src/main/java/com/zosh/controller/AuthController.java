package com.zosh.controller;


import com.zosh.configrations.JwtProvider;
import com.zosh.exception.UserException;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.request.ForgotPasswordRequest;
import com.zosh.payload.request.LoginDto;
import com.zosh.payload.request.ResetPasswordRequest;
import com.zosh.payload.response.ApiResponse;
import com.zosh.payload.response.ApiResponseBody;

import com.zosh.payload.response.AuthResponse;
import com.zosh.repository.UserRepository;

import com.zosh.service.AuthService;
import com.zosh.service.UserService;
import com.zosh.service.impl.CustomUserImplementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;
    private final UserService userService;
    private final AuthService authService;

    // ================= POST endpoints =================
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseBody<AuthResponse>> signupHandler(
            @RequestBody @Valid UserDTO req) throws UserException {
        AuthResponse response = authService.signup(req);
        return ResponseEntity.ok(new ApiResponseBody<>(true,
                "User created successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseBody<AuthResponse>> loginHandler(
            @RequestBody LoginDto req) throws UserException {
        AuthResponse response = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(new ApiResponseBody<>(
                true,
                "User logged in successfully",
                response));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestBody ForgotPasswordRequest request) throws UserException {
        authService.createPasswordResetToken(request.getEmail());
        return ResponseEntity.ok(new ApiResponse("A Reset link was sent to your email."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getToken(), request.getPassword());
        return ResponseEntity.ok(new ApiResponse("Password reset successful"));
    }

    // ================= GET endpoints (temporary) =================
    @GetMapping("/signup")
    public ResponseEntity<String> signupGet() {
        return ResponseEntity.ok("This endpoint expects a POST request with JSON body");
    }

    @GetMapping("/onboarding")
    public ResponseEntity<String> onboardingGet() {
        return ResponseEntity.ok("This endpoint expects a POST request with JSON body");
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginGet() {
        return ResponseEntity.ok("This endpoint expects a POST request with JSON body");
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPasswordGet() {
        return ResponseEntity.ok("This endpoint expects a POST request with JSON body");
    }

    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPasswordGet() {
        return ResponseEntity.ok("This endpoint expects a POST request with JSON body");
    }
}

