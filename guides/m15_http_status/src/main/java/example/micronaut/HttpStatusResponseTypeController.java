package example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;

@Controller("/http-status-return-type")
class HttpStatusResponseTypeController {
    @Get
    HttpStatus index() {
        return HttpStatus.ACCEPTED;
    }
}
