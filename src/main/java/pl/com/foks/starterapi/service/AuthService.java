package pl.com.foks.starterapi.service;


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
import pl.com.foks.starterapi.dto.AuthRequest;
import pl.com.foks.starterapi.entity.User;
import pl.com.foks.starterapi.repository.UserRepository;
import pl.com.foks.starterapi.security.JwtUtils;
import pl.com.foks.starterapi.exception.InvalidCredentialsException;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private AuthenticationManager authenticationManager;

    @Transactional
    public String register(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidCredentialsException("Email already in use");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }

    @Transactional(readOnly = true)
    public String login(AuthRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException exception) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtUtils.generateJwtToken(userDetails);
    }
}
