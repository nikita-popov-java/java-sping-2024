package ru.nikitapopov.skillbox.mod4.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private String author;
    private Long newsId;
}

