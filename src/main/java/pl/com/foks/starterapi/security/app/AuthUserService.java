package pl.com.foks.starterapi.security.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jackson.JsonMixin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.com.foks.starterapi.users.app.UserService;
import pl.com.foks.starterapi.users.domain.User;

@Component
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {
    private final UserService userService;

    /**
     * Loads user details by email.
     *
     * @param email the email of the user
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
