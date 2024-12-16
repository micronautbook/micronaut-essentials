package com.micronautbook.essentials;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class GetPostsTest {

    @DisabledInNativeImage
    @Test
    void fetchPosts(PostClient postClient) {
        var posts = postClient.fetchPosts();
        assertNotNull(posts);
        assertEquals(100, posts.size());
    }
}