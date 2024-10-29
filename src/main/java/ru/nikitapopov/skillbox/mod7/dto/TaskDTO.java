package ru.nikitapopov.skillbox.mod7.dto;

import lombok.Data;
import ru.nikitapopov.skillbox.mod7.enums.TaskStatus;

import java.time.Instant;
import java.util.Set;

@Data
public class TaskDTO {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private TaskStatus status;
    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;
}