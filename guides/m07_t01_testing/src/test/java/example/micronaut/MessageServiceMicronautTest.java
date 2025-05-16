package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(startApplication = false)
class MessageServiceMicronautTest {

    @Test
    void applicationContextWithoutMicronautTest(MessageService messageService) {
        assertEquals("Hello World", messageService.message());
    }
}