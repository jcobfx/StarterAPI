package pl.com.foks.starterapi.users.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.foks.starterapi.users.domain.User;
import pl.com.foks.starterapi.users.domain.UserExistsException;
import pl.com.foks.starterapi.users.domain.UserNotFoundException;
import pl.com.foks.starterapi.users.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserExistsException(user.getEmail());
        }
        return userRepository.save(user);
    }
}
