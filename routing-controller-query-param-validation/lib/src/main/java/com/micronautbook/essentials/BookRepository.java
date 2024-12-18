package com.micronautbook.essentials;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface BookRepository {

    void save(@NotNull @Valid Book book);
}
