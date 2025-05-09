package pl.com.foks.starterapi.tasks.dto;

import java.time.LocalDateTime;

public record TaskRequest(
        String title,
        String description,
        LocalDateTime dueDate
) {}
