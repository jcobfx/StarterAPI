package pl.com.foks.starterapi.tasks.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.foks.starterapi.users.domain.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByUser(User user);
    Optional<Task> findByIdAndUser(String id, User user);
}
