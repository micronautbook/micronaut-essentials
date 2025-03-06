package example.micronaut;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Produces;

@Controller
class UserAgentController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/user-agent")
    String userAgent(@Header(HttpHeaders.USER_AGENT) String ua) {
        return ua;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/user-agent-no-value")
    String userAgentNoValue(@Header String userAgent) {
        return userAgent;
    }
}
