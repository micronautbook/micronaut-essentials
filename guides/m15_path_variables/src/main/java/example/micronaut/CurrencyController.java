package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.serde.annotation.Serdeable;
import java.math.BigDecimal;
import java.util.Currency;

@Controller
class CurrencyController {

    @Get("/exchange/{fromCurrencyCode}/{toCurrencyCode}/")
    Exchange exchange(@PathVariable(value = "fromCurrencyCode", defaultValue = "USD") String fromCode,
                      @PathVariable(value = "toCurrencyCode", defaultValue = "EUR") String toCode) {
        Currency from = Currency.getInstance(fromCode);
        Currency to = Currency.getInstance(toCode);
        //TODO calculate exchange
        BigDecimal exchange = new BigDecimal("0.91");
        return new Exchange(from.getCurrencyCode(), to.getCurrencyCode(), exchange);
    }

    @Serdeable
    record Exchange(String from, String to, BigDecimal exchange) {}
}
