package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import java.util.Map;

@Controller
class UtmController {
    @Get("/utm")
    Map<String, String> index(@QueryValue("utm_source") String source) {
        return Map.of("source", source);
    }

    @Get("/utm/noann")
    Map<String, String> noann(String utm_source) {
        return Map.of("source", utm_source);
    }
}
