package ru.nikitapopov.skillbox.mod2.service;

import org.springframework.stereotype.Service;
import ru.nikitapopov.skillbox.mod2.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long currentId = 1;

    public Student addStudent(String firstName, String lastName, int age) {
        var student = new Student(currentId++, firstName, lastName, age);

        students.put(student.getId(), student);

        return student;
    }

    public Student removeStudent(Long id) {
        if (!students.containsKey(id))
            throw new IllegalArgumentException("Студента с id=%d не существует!".formatted(id));

        return students.remove(id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public void clearStudents() {
        students.clear();
    }
}