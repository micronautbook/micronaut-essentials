package com.micronautbook.essentials;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class HomeControllerTest {
    @Test
    void testHomePage(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        List<Post> posts = assertDoesNotThrow(() -> client.retrieve(HttpRequest.GET("/"), Argument.listOf(Post.class)));
        assertEquals(5, posts.size());
    }
}