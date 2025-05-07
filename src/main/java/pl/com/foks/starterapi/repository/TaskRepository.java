package pl.com.foks.starterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.foks.starterapi.entity.Task;
import pl.com.foks.starterapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    Optional<Task> findByIdAndUser(Long id, User user);
}
