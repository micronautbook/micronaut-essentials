package com.micronautbook.essentials;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;

@Singleton
class OutOfStockExceptionHandler implements ExceptionHandler<OutOfStockException, HttpResponse> {
    private final ErrorResponseProcessor<?> errorResponseProcessor;

    OutOfStockExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse handle(HttpRequest request, OutOfStockException exception) {
        return errorResponseProcessor.processResponse(ErrorContext.builder(request)
                .cause(exception)
                .errorMessage("No stock available")
                .build(), HttpResponse.unprocessableEntity());
    }
}
