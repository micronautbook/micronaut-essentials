package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/http-response")
class HttpResponseController {
    @Get
    HttpResponse<?> index() {
        return HttpResponse.status(HttpStatus.ACCEPTED);
    }
}
