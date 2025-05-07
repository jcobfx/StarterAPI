package pl.com.foks.starterapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.foks.starterapi.entity.User;
import pl.com.foks.starterapi.repository.UserRepository;
import pl.com.foks.starterapi.exception.UserNotFoundException;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
