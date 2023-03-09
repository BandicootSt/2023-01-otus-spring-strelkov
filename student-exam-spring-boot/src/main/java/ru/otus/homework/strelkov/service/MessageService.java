package ru.otus.homework.strelkov.service;

public interface MessageService {

    String getStudentRequestFirstNameMessage();

    String getStudentRequestLastNameMessage();

    String getExamStartMessage();

    String getExamResultSuccessMessage(String studentName, int score);

    String getExamResultFailedMessage(String studentName, int score);
}
