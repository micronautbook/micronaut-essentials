package com.micronautbook.essentials;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(startApplication = false)
class BlogConfigurationEnvironmentVariableTest {

    @EnabledIfEnvironmentVariable(named = "BLOG_HOME_POSTS", matches = "20")
    @Test
    void blogConfiguration(BlogConfiguration conf) {
        assertEquals(20, conf.getHomePosts());

    }
}