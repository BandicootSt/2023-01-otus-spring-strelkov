package ru.otus.homework5.strelkov.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@AllArgsConstructor
@EqualsAndHashCode
public class Genre {

    @Nullable
    Long id;

    @Nonnull
    String name;

    public Genre (String name) {
        this.id = null;
        this.name = name;
    }

}
