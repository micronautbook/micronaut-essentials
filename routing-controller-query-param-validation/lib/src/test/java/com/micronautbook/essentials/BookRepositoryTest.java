package com.micronautbook.essentials;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "spec.name", value = "BookRepositoryTest")
@MicronautTest(startApplication = false)
class BookRepositoryTest {
    @Test
    void testBookRepositoryValidation(BookRepository bookRepository) {
        assertThrows(ConstraintViolationException.class, () -> bookRepository.save(new Book("", "9781617291470", 263)));
        assertThrows(ConstraintViolationException.class, () -> bookRepository.save(null));
    }

    @Requires(property = "spec.name", value = "BookRepositoryTest")
    @Replaces(BookRepository.class)
    @Singleton
    static class BookRepositoryReplacement implements BookRepository {
        @Override
        public void save(@NotNull @Valid Book book) {
        }
    }
}