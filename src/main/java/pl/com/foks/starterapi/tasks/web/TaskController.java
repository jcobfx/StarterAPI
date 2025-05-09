package pl.com.foks.starterapi.tasks.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.com.foks.starterapi.tasks.app.TaskService;
import pl.com.foks.starterapi.tasks.domain.Task;
import pl.com.foks.starterapi.tasks.dto.TaskRequest;
import pl.com.foks.starterapi.users.domain.User;
import pl.com.foks.starterapi.users.app.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Validated
@Tag(name = "Tasks", description = "Operations on tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    private final UserService userService;

    @Operation(summary = "Get all tasks for the authenticated user")
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.ok(taskService.getTasksForUser(user));
    }

    @Operation(summary = "Create a new task for the authenticated user")
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody @Valid TaskRequest taskRequest, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.ok(taskService.createTask(taskRequest, user).getId());
    }

    @Operation(summary = "Update an existing task for the authenticated user")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable String id, @RequestBody @Valid TaskRequest taskRequest, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        taskService.updateTask(id, taskRequest, user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a task for the authenticated user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        taskService.deleteTask(id, user);
        return ResponseEntity.noContent().build();
    }
}
