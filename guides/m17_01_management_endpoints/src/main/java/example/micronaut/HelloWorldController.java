package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

@Controller
class HelloWorldController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);
    private static final Map<String, Object> HELLO_WORLD =
            Collections.singletonMap("message", "Hello World");

    @Get
    Map<String, Object> index() {
        LOG.trace("inside the hello world controller");
        return HELLO_WORLD;
    }
}
