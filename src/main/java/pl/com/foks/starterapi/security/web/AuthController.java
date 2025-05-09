package pl.com.foks.starterapi.security.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.foks.starterapi.security.app.AuthService;
import pl.com.foks.starterapi.security.dto.AuthRequest;
import pl.com.foks.starterapi.security.dto.AuthResponse;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Operations for user authentication")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        String id = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @Operation(summary = "Authorize an existing user")
    @PostMapping
    public ResponseEntity<AuthResponse> authorize(@RequestBody AuthRequest request) {
        String token = authService.authorize(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
