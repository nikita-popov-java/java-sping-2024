package ru.nikitapopov.skillbox.mod4.mapper;

import org.mapstruct.Mapper;
import ru.nikitapopov.skillbox.mod4.dto.UserDTO;
import ru.nikitapopov.skillbox.mod4.model.User;

//@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
