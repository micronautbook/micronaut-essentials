package com.micronautbook.essentials;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class BookControllerTest {
    @Test
    void testShow(@Client("/")HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String json = assertDoesNotThrow(() -> client.retrieve("/books/9781617291470"));
        assertEquals("{\"name\":\"Netty in Action\",\"isbn\":\"9781617291470\",\"pages\":263}", json);

        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> client.retrieve("/books/9781491910771"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }
}