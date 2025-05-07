package pl.com.foks.starterapi.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task with ID: " + taskId + " not found");
    }
}
