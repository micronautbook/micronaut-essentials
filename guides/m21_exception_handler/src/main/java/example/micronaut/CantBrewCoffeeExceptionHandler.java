package example.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.server.exceptions.ErrorResponseProcessorExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;

@Singleton
public class CantBrewCoffeeExceptionHandler extends ErrorResponseProcessorExceptionHandler<CantBrewCoffeeException> {
    protected CantBrewCoffeeExceptionHandler(ErrorResponseProcessor<?> responseProcessor) {
        super(responseProcessor);
    }

    @Override
    protected @NonNull MutableHttpResponse<?> createResponse(CantBrewCoffeeException exception) {
        return HttpResponse.status(HttpStatus.I_AM_A_TEAPOT);
    }
}
