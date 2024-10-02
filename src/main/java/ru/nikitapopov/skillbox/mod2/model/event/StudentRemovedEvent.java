package ru.nikitapopov.skillbox.mod2.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentRemovedEvent {
    private final Long id;
}
