package com.micronautbook.essentials;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(startApplication = false)
class BlogConfigurationDefaultTest {

    @Test
    void blogConfiguration(BlogConfiguration conf) {
        assertEquals(10, conf.getHomePosts());
    }
}