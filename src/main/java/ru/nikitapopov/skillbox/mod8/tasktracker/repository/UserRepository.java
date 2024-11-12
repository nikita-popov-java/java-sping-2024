package ru.nikitapopov.skillbox.mod8.tasktracker.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.nikitapopov.skillbox.mod8.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);

    Mono<User> findByEmail(String email);

    Mono<User> findByIdAndRolesContains(String id, String role);
}
