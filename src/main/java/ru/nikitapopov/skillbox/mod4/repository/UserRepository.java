package ru.nikitapopov.skillbox.mod4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitapopov.skillbox.mod4.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
