package ru.otus.homework.strelkov.service.impl;

import ru.otus.homework.strelkov.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams(PrintStream outputStream, InputStream inputStream) {
        this.output = outputStream;
        this.input = new Scanner(inputStream);
    }

    @Override
    public void outputString(String s){
        output.println(s);
    }
}
