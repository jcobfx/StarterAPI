package pl.com.foks.starterapi.tasks.domain;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task with ID: " + taskId + " not found");
    }
}
