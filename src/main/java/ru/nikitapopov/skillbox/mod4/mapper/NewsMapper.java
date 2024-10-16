package ru.nikitapopov.skillbox.mod4.mapper;

import ru.nikitapopov.skillbox.mod4.dto.NewsDTO;
import ru.nikitapopov.skillbox.mod4.model.News;

//@Mapper(componentModel = "spring")
public interface NewsMapper {
//    @Mapping(target = "author", source = "author.username")
//    @Mapping(target = "category", source = "category.name")
    NewsDTO toDto(News news);
//    @Mapping(target = "author.id", source = "author")
//    @Mapping(target = "category.id", source = "category")
    News toEntity(NewsDTO newsDTO);
}