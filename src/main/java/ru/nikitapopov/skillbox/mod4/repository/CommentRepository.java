package ru.nikitapopov.skillbox.mod4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitapopov.skillbox.mod4.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}