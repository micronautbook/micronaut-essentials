package example.micronaut;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller
class UserAgentController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/user-agent")
    String userAgent(HttpRequest<?> request) {
        return request.getHeaders().get(HttpHeaders.USER_AGENT);
    }
}
