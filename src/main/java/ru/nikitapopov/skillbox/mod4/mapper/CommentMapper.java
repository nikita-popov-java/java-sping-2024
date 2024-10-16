package ru.nikitapopov.skillbox.mod4.mapper;

import ru.nikitapopov.skillbox.mod4.dto.CommentDTO;
import ru.nikitapopov.skillbox.mod4.model.Comment;

//@Mapper(componentModel = "spring")
public interface CommentMapper {
//    @Mapping(target = "author", source = "author.username")
    CommentDTO toDto(Comment comment);
//    @Mapping(target = "author.username", source = "author")
    Comment toEntity(CommentDTO commentDTO);
}