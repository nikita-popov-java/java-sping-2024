package ru.nikitapopov.skillbox.mod7.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nikitapopov.skillbox.mod7.dto.TaskDTO;
import ru.nikitapopov.skillbox.mod7.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "observers", ignore = true)
    Task toTask(TaskDTO taskDTO);

    TaskDTO toTaskDTO(Task task);
}