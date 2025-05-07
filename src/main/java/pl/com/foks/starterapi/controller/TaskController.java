package pl.com.foks.starterapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.com.foks.starterapi.entity.Task;
import pl.com.foks.starterapi.entity.User;
import pl.com.foks.starterapi.service.TaskService;
import pl.com.foks.starterapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
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
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.ok(taskService.createTask(task, user));
    }

    @Operation(summary = "Update an existing task for the authenticated user")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody @Valid Task task, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.ok(taskService.updateTask(id, task, user));
    }

    @Operation(summary = "Delete a task for the authenticated user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        taskService.deleteTask(id, user);
        return ResponseEntity.noContent().build();
    }
}
