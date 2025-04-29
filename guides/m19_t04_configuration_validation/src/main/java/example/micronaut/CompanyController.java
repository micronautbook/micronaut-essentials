package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Map;

@Controller("/company")
class CompanyController {
    private final CompanyConfiguration configuration;

    CompanyController(CompanyConfiguration configuration) {
        this.configuration = configuration;
    }

    @Get
    Map<String, Integer> index() {
        return Map.of("vat", configuration.getValueAddedTax());
    }
}
