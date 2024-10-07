package ru.nikitapopov.skillbox.mod4.dto;

import lombok.Data;

@Data
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String category;
    private int commentCount;
}

