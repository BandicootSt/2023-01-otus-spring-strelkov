package ru.otus.homework.strelkov.service;

import ru.otus.homework.strelkov.domain.Student;

public interface StudentService {

    Student requestStudentName();

    String getStudentNameAsString(Student student);
}
