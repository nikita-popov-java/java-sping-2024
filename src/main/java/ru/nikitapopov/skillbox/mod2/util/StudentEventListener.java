package ru.nikitapopov.skillbox.mod2.util;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.nikitapopov.skillbox.mod2.model.event.StudentCreatedEvent;
import ru.nikitapopov.skillbox.mod2.model.event.StudentRemovedEvent;

//@Component
public class StudentEventListener {

    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {
        System.out.println("Создан студент: " + event.getStudent());
    }

    @EventListener
    public void handleStudentRemoved(StudentRemovedEvent event) {
        System.out.println("Удалён студент с id: " + event.getId());
    }
}
