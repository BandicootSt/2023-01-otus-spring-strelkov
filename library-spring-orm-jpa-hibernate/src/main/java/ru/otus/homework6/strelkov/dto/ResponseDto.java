package ru.otus.homework6.strelkov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder
@Value
public class ResponseDto <T>{

    CommandResultStatus status;

    String errMsg;

    T result;
}
