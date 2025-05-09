package pl.com.foks.starterapi.tasks.domain;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String taskId) {
        super("Task with ID: " + taskId + " not found");
    }
}
