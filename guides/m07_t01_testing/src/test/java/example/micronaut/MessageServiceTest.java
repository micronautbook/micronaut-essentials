package example.micronaut;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    @Test
    void applicationContextWithoutMicronautTest() {
        try (ApplicationContext ctx = ApplicationContext.run()) {
            MessageService messageService = ctx.getBean(MessageService.class);
            assertEquals("Hello World", messageService.message());
        }
    }
}