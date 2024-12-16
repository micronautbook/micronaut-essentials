package com.micronautbook.essentials;

import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BookResourceTest {
    @Test
    void testList(@Client("/")HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String json = client.retrieve("/books/list");
        assertEquals("[{\"name\":\"Netty in Action\",\"isbn\":\"9781617291470\",\"pages\":263}]", json);
    }
}