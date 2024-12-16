package com.micronautbook.essentials;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class BookControllerTest {
    @Test
    void testList(@Client("/")HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        URI uri = UriBuilder.of("/books").path("list").build();
        String json = client.retrieve(HttpRequest.GET(uri));
        assertEquals("[{\"name\":\"Netty in Action\",\"isbn\":\"9781617291470\",\"pages\":263},{\"name\":\"Head First Java\",\"isbn\":\"9781491910771\",\"pages\":716}]", json);

        uri = UriBuilder.of("/books").path("list").queryParam("max", 1).build();
        json = client.retrieve(HttpRequest.GET(uri));
        assertEquals("[{\"name\":\"Netty in Action\",\"isbn\":\"9781617291470\",\"pages\":263}]", json);

        HttpClientResponseException ex  = assertThrows(HttpClientResponseException.class,
                () -> client.retrieve(HttpRequest.GET(UriBuilder.of("/books")
                        .path("list")
                        .queryParam("max", -1)
                        .build())));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }
}