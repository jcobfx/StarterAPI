package pl.com.foks.starterapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.foks.starterapi.dto.AuthRequest;
import pl.com.foks.starterapi.dto.AuthResponse;
import pl.com.foks.starterapi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Operations for user authentication")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        return new AuthResponse(authService.register(request));
    }

    @Operation(summary = "Login an existing user")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
