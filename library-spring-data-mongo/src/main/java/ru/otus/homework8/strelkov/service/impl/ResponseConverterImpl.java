package ru.otus.homework8.strelkov.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import ru.otus.homework8.strelkov.dto.CommandResultStatus;
import ru.otus.homework8.strelkov.dto.ResponseDto;
import ru.otus.homework8.strelkov.exception.ResponseSerializeException;
import ru.otus.homework8.strelkov.service.ResponseConverter;

@RequiredArgsConstructor
public class ResponseConverterImpl implements ResponseConverter {

    private final ObjectMapper mapper;

    @Override
    public String fromSuccess() {
        return fromSuccess(null);
    }

    @Override
    public <T> String fromSuccess(@Nullable T result) {
        ResponseDto<?> response = ResponseDto.builder()
            .status(CommandResultStatus.SUCCESS)
            .result(result)
            .build();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new ResponseSerializeException("Failed while trying to serialize response for object: " + response.toString(), e);
        }
    }

    @Override
    public String fromError(@Nonnull String errMsg) {
        ResponseDto<?> response = ResponseDto.builder()
            .status(CommandResultStatus.FAILED)
            .errMsg(errMsg)
            .build();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new ResponseSerializeException("Failed while trying to serialize response for object: " + response.toString(), e);
        }
    }
}
