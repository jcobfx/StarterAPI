package pl.com.foks.starterapi.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String email;
    private String password;
}
