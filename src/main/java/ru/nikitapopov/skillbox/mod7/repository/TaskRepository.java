package ru.nikitapopov.skillbox.mod7.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.nikitapopov.skillbox.mod7.model.Task;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, String> {
    Flux<Task> findAllByAuthorId(String authorId);
    Flux<Task> findAllByAssigneeId(String assigneeId);
}