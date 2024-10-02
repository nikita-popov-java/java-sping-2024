package ru.nikitapopov.skillbox.mod2.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.nikitapopov.skillbox.mod2.model.event.StudentCreatedEvent;
import ru.nikitapopov.skillbox.mod2.model.event.StudentRemovedEvent;
import ru.nikitapopov.skillbox.mod2.service.StudentService;

//@ShellComponent
@RequiredArgsConstructor
public class StudentCommands {

    private final ApplicationEventPublisher publisher;
    private final StudentService studentService;

    @ShellMethod(value = "Добавить нового студента", key = {"addStudent"})
    public void addStudent(String firstName, String lastName, int age) {
        var student = studentService.addStudent(firstName, lastName, age);

        publisher.publishEvent(new StudentCreatedEvent(student));
    }

    @ShellMethod(value = "Удалить студента по id", key = {"removeStudent"})
    public void removeStudent(Long id) {
        var student = studentService.removeStudent(id);

        publisher.publishEvent(new StudentRemovedEvent(student.getId()));
    }

    @ShellMethod(value = "Вывести всех студентов", key = {"listStudents"})
    public String listStudents() {
        return studentService.getAllStudents().toString();
    }

    @ShellMethod(value = "Очистить список студентов", key = {"clearStudents"})
    public String clearStudents() {
        studentService.clearStudents();
        return "Список студентов очищен.";
    }
}
