package pl.com.foks.starterapi.security.app;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.foks.starterapi.security.dto.AuthRequest;
import pl.com.foks.starterapi.users.app.UserService;
import pl.com.foks.starterapi.users.domain.User;
import pl.com.foks.starterapi.security.domain.InvalidCredentialsException;
import pl.com.foks.starterapi.security.infra.JwtUtils;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private AuthenticationManager authenticationManager;

    @Transactional
    public String register(AuthRequest request) {
        if (userService.existsByEmail(request.email())) {
            throw new InvalidCredentialsException("Email already in use");
        }
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .build();
        return userService.createUser(user).getId();
    }

    @Transactional(readOnly = true)
    public String authorize(AuthRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        } catch (AuthenticationException exception) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtUtils.generateJwtToken(userDetails);
    }
}
