package example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

@Controller("/teapot")
class TeapotController {

    @Post("/brew")
    @Status(HttpStatus.ACCEPTED)
    void save(@Body("type") String type) {
        if (type.equals("coffee")) {
            throw new CantBrewCoffeeException();
        }
    }
}
