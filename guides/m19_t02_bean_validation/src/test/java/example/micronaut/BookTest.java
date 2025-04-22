package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class BookTest {
    @Test
    void testBook(Validator validator) {
        Book b = new Book("Netty in Action", "9781617291470", 263);
        assertTrue(validator.validate(b).isEmpty());

        // pages and isbn are optional
        b = new Book("Netty in Action", null, null);
        assertTrue(validator.validate(b).isEmpty());

        // name cannot be blank
        b = new Book("", "9781617291470", 263);
        assertFalse(validator.validate(b).isEmpty());

        // name cannot be null
        b = new Book(null, "9781617291470", 263);
        assertFalse(validator.validate(b).isEmpty());

        // pages must be positive
        b = new Book("Netty in Action", "9781617291470", 0);
        assertFalse(validator.validate(b).isEmpty());
    }
}