package com.micronautbook.essentials;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class GetPostsTest {

    @Test
    void fetchPosts(PostClient postClient) {
        var posts = postClient.fetchPosts();
        assertNotNull(posts);
        assertEquals(100, posts.size());

        posts = postClient.fetchPostsExchange();
        assertEquals(100, posts.size());
    }

    @Test
    void noExistingPost(PostClient postClient) {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> postClient.findById("999"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }
}