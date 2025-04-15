package com.micronautbook.essentials;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "spec.name", value = "OutOfStockExceptionTest")
@MicronautTest
class OutOfStockExceptionTest {

    @Test
    void outOfStock(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class,
                () -> client.exchange(HttpRequest.GET("/outofstock")));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, ex.getStatus());
    }

    @Requires(property = "spec.name", value = "OutOfStockExceptionTest")
    @Controller("/outofstock")
    static class OutOfStockExceptionController {
        @Get
        void index() {
            throw new OutOfStockException();
        }
    }
}