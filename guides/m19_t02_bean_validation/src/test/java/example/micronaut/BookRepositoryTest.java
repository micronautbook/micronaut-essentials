package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class BookRepositoryTest {
    @Test
    void bookIsValidated(BookRepository bookRepository) {
        assertDoesNotThrow(() ->
                bookRepository.save(new Book("Netty in Action", "9781617291470", 263)));

        // pages and isbn are optional
        assertDoesNotThrow(() ->
                bookRepository.save(new Book("Netty in Action", null, null)));

        // book cannot be null
        assertThrows(ConstraintViolationException.class, () ->
                bookRepository.save(null));

        // name cannot be blank
        assertThrows(ConstraintViolationException.class, () ->
                bookRepository.save(new Book("", "9781617291470", 263)));

        // name cannot be null
        assertThrows(ConstraintViolationException.class, () ->
                bookRepository.save(new Book(null, "9781617291470", 263)));

        // pages must be positive
        assertThrows(ConstraintViolationException.class, () ->
                bookRepository.save(new Book("Netty in Action", "9781617291470", 0)));
    }
}