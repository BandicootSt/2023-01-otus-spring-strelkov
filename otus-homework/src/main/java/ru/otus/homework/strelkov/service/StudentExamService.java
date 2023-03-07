package ru.otus.homework.strelkov.service;

import ru.otus.homework.strelkov.domain.StudentName;

public interface StudentExamService {

    void examineStudent();

    StudentName requestStudentName();
}
