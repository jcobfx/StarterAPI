package pl.com.foks.starterapi.tasks.app;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.foks.starterapi.tasks.domain.TaskNotFoundException;
import pl.com.foks.starterapi.tasks.domain.Task;
import pl.com.foks.starterapi.tasks.domain.TaskRepository;
import pl.com.foks.starterapi.tasks.dto.TaskRequest;
import pl.com.foks.starterapi.users.domain.User;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Transactional
    public Task createTask(TaskRequest taskRequest, User user) {
        Task task = Task.builder()
                .id(UUID.randomUUID().toString())
                .title(taskRequest.title())
                .description(taskRequest.description())
                .dueDate(taskRequest.dueDate())
                .user(user)
                .build();
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(String id, TaskRequest taskRequest, User user) {
        Task existingTask = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException(id));
        existingTask.setTitle(taskRequest.title());
        existingTask.setDescription(taskRequest.description());
        existingTask.setDueDate(taskRequest.dueDate());
        return taskRepository.save(existingTask);
    }

    @Transactional
    public void deleteTask(String id, User user) {
        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }
}
