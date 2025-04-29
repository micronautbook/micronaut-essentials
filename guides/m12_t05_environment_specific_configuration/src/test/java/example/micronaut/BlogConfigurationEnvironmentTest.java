package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = "prod")
class BlogConfigurationEnvironmentTest {

    @Test
    void test(BlogConfiguration blogConfiguration) {
        assertEquals(10, blogConfiguration.getPostsPerPage());
    }
}