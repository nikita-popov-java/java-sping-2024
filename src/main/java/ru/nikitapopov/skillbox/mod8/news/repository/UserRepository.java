package ru.nikitapopov.skillbox.mod8.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitapopov.skillbox.mod8.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
