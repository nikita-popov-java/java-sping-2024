package ru.nikitapopov.skillbox.mod2.util;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.nikitapopov.skillbox.mod2.service.StudentService;

@ShellComponent
@RequiredArgsConstructor
public class StudentCommands {

    private final StudentService studentService;

    @ShellMethod(value = "Добавить нового студента", key = {"addStudent"})
    public String addStudent(String firstName, String lastName, int age) {
        var student = studentService.addStudent(firstName, lastName, age);

        return "Добавлен студент: " + student;
    }

    @ShellMethod(value = "Удалить студента по id", key = {"removeStudent"})
    public String removeStudent(Long id) {
        var student = studentService.removeStudent(id);

        return student != null
                ? "Удалён студент: " + student
                : "Студент с id " + id + " не найден.";

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
