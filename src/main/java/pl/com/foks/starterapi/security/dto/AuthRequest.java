package pl.com.foks.starterapi.security.dto;

public record AuthRequest(
        String username,
        String email,
        String password
) {}
