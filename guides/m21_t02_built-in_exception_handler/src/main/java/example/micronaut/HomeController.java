package example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;
import jakarta.validation.Valid;

@Controller
class HomeController {

    @Get("/ex")
    @Status(HttpStatus.OK)
    void exceptionWithoutBuiltInHandler() {
        throw new UnsupportedOperationException("there is no built-in exception Handler for UnsupportedOperationException");
    }

    @Get("/builtInEx")
    @Status(HttpStatus.OK)
    void builtInEx() {
        Person invalidPerson = new Person("");
        validatePerson(invalidPerson);
    }

    void validatePerson(@Valid Person person) {
    }
}
