package pl.com.foks.starterapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.foks.starterapi.exception.TaskNotFoundException;
import pl.com.foks.starterapi.entity.Task;
import pl.com.foks.starterapi.entity.User;
import pl.com.foks.starterapi.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Transactional
    public Task createTask(Task task, User user) {
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task task, User user) {
        Task existingTask = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException(id));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        return taskRepository.save(existingTask);
    }

    @Transactional
    public void deleteTask(Long id, User user) {
        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }
}
