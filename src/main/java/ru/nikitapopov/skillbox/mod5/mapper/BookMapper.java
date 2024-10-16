package ru.nikitapopov.skillbox.mod5.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nikitapopov.skillbox.mod5.dto.BookDTO;
import ru.nikitapopov.skillbox.mod5.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "category.name", source = "category")
    Book toEntity(BookDTO bookDTO);
}
