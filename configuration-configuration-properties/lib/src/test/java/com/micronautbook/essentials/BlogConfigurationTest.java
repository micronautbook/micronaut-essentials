package com.micronautbook.essentials;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Property(name = "blog.home-posts", value = "20")
@MicronautTest(startApplication = false)
class BlogConfigurationTest {

    @Test
    void blogConfiguration(BlogConfiguration blogConfiguration) {
        assertEquals(20, blogConfiguration.getHomePosts());
    }
}