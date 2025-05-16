package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

@Controller
class GreetingController {
    private final MessageService messageService;
    GreetingController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Get
    Map<String, Object> index() {
        return Collections.singletonMap("message", messageService.message());
    }
}
