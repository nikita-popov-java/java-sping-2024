package ru.nikitapopov.skillbox.mod4.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
}

