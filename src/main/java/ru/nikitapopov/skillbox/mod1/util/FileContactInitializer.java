package ru.nikitapopov.skillbox.mod1.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.nikitapopov.skillbox.mod1.service.ContactService;

import java.io.IOException;

@Component
@Profile("init")
@RequiredArgsConstructor
public class FileContactInitializer {

    private final ContactService contactService;

    @PostConstruct
    public void init() {
        try {
            contactService.loadContactsFromFile();
        } catch (IOException e) {
            System.out.println("Ошибка загрузки контактов: " + e.getMessage());
        }
    }
}
