package example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;

@Controller("/status-annotation")
class StatusAnnotationController {
    @Status(HttpStatus.ACCEPTED)
    @Get
    void index() {
    }
}
