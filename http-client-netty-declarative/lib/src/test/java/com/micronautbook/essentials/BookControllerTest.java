package com.micronautbook.essentials;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class BookControllerTest {

    @Test
    void badRequest(BookClient bookClient) {
        assertEquals(HttpStatus.ACCEPTED, bookClient.save(new Book("Building Microservices")));

        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> bookClient.save(new Book("")));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }
}