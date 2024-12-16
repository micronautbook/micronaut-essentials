package com.micronautbook.essentials;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Book(String name, String isbn, Integer pages) {
}
