package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Student;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Override
    public Student requestStudentName() {
        ioService.outputString("Enter your first name");
        String firstName = ioService.inputString();
        ioService.outputString("Enter your last name");
        return new Student(firstName, ioService.inputString());
    }

    @Override
    public String getStudentNameAsString(Student student) {
        return student.getLastName() + " " + student.getFirstName();
    }
}
