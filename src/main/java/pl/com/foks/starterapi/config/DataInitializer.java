package pl.com.foks.starterapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.foks.starterapi.tasks.domain.Task;
import pl.com.foks.starterapi.users.domain.User;
import pl.com.foks.starterapi.tasks.domain.TaskRepository;
import pl.com.foks.starterapi.users.domain.UserRepository;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("test@example.com").isEmpty()) {
                User user = new User();
                user.setUsername("testuser");
                user.setEmail("test@example.com");
                user.setPassword(passwordEncoder.encode("test123"));
                user.setRole("USER");
                userRepository.save(user);

                if (taskRepository.findByUser(user).isEmpty()) {
                    for (int i = 1; i <= 5; i++) {
                        Task task = Task.builder()
                                .title("Task " + i)
                                .description("Description for task " + i)
                                .dueDate(LocalDate.now().plusDays(i).atStartOfDay())
                                .user(user)
                                .build();
                        taskRepository.save(task);
                    }
                }
            }
        };
    }
}
