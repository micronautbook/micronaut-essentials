package com.micronautbook.essentials.property;

import com.micronautbook.essentials.Post;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Property(name = "blog.home-posts", value = "5")
@MicronautTest
class HomeControllerTest {
    @Test
    void testHomePage(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        List<Post> posts = assertDoesNotThrow(() -> client.retrieve(HttpRequest.GET("/property"), Argument.listOf(Post.class)));
        assertEquals(5, posts.size());
    }
}