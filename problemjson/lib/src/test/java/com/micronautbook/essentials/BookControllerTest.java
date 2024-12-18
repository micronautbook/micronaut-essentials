package com.micronautbook.essentials;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class BookControllerTest {
    @Test
    void testSave(@Client("/")HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String payload = "{\"name\":\"Netty in Action\",\"isbn\":\"9781617291470\",\"pages\":0}";
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class,
                () -> client.exchange(HttpRequest.POST(UriBuilder.of("/books").path("save").build(), payload)));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Optional<String> jsonOptional = ex.getResponse().getBody(String.class);
        assertTrue(ex.getResponse().getContentType().isPresent());
        assertEquals(MediaType.APPLICATION_JSON_PROBLEM_TYPE, ex.getResponse().getContentType().get());
        assertTrue(jsonOptional.isPresent());
        String json = jsonOptional.get();
        assertNotNull(json);
        assertEquals("{\"type\":\"https://zalando.github.io/problem/constraint-violation\",\"title\":\"Constraint Violation\",\"status\":400,\"violations\":[{\"field\":\"save.book.pages\",\"message\":\"must be greater than 0\"}]}", json);
    }
}