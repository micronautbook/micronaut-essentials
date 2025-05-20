package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

import java.util.Collections;
import java.util.Map;
@Controller("/hello")
class HelloWorldController {

    @Get
    Map<String, Object> index() {
        return Map.of("message", "Hello World");
    }

    @Post
    Map<String, Object> defaultIsJson() {
        return Collections.singletonMap("message", "By default, Micronaut answers with application/json content type");
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    Map<String, Object> youCanSpecify() {
        return Collections.singletonMap("message", "You can specify the request's content-type that your method accepts with @Consumes");
    }
}
