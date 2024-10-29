package ru.nikitapopov.skillbox.mod7.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nikitapopov.skillbox.mod7.dto.TaskDTO;
import ru.nikitapopov.skillbox.mod7.mapper.TaskMapper;
import ru.nikitapopov.skillbox.mod7.model.Task;
import ru.nikitapopov.skillbox.mod7.model.User;
import ru.nikitapopov.skillbox.mod7.repository.TaskRepository;
import ru.nikitapopov.skillbox.mod7.repository.UserRepository;

import java.time.Instant;
import java.util.HashSet;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @GetMapping
    public Flux<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .flatMap(this::populateTaskRelations)
                .map(taskMapper::toTaskDTO);
    }

    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable String id) {
        return taskRepository.findById(id)
                .flatMap(this::populateTaskRelations)
                .map(taskMapper::toTaskDTO);
    }

    @PostMapping
    public Mono<Task> createTask(@RequestBody TaskDTO taskDTO) {
        var task = taskMapper.toTask(taskDTO);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Mono<Task> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    existingTask.setName(taskDTO.getName());
                    existingTask.setDescription(taskDTO.getDescription());
                    existingTask.setStatus(taskDTO.getStatus());
                    existingTask.setUpdatedAt(Instant.now());
                    return taskRepository.save(existingTask);
                });
    }

    @PostMapping("/{id}/observer/{userId}")
    public Mono<Task> addObserverToTask(@PathVariable String id, @PathVariable String userId) {
        return taskRepository.findById(id)
                .flatMap(task -> {
                    task.getObserverIds().add(userId);
                    return taskRepository.save(task);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable String id) {
        return taskRepository.deleteById(id);
    }

    private Mono<Task> populateTaskRelations(Task task) {
        var authorMono = userRepository.findById(task.getAuthorId()).defaultIfEmpty(new User());
        var assigneeMono = userRepository.findById(task.getAssigneeId()).defaultIfEmpty(new User());
        var observersFlux = Flux.fromIterable(task.getObserverIds())
                .flatMap(userRepository::findById);

        return Mono.zip(authorMono, assigneeMono, observersFlux.collectList())
                .map(tuple -> {
                    task.setAuthor(tuple.getT1());
                    task.setAssignee(tuple.getT2());
                    task.setObservers(new HashSet<>(tuple.getT3()));
                    return task;
                });
    }
}
