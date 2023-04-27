package ru.otus.homework5.strelkov.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

    @Nullable
    private Long id;

    @Nonnull
    private final String name;

    public Genre (String name) {
        this.name = name;
    }

}
