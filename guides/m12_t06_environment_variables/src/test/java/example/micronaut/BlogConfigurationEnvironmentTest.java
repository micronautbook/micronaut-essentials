package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlogConfigurationEnvironmentTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "BLOG_POSTS_PER_PAGE", matches = ".*")
    void testEnvironmentVariable() {
        String postsPerPageEnvironmentVariable = System.getenv("BLOG_POSTS_PER_PAGE");
        Integer postsPerPage = assertDoesNotThrow(() -> Integer.parseInt(postsPerPageEnvironmentVariable));
        try (ApplicationContext ctx = ApplicationContext.run()) {
            BlogConfiguration blogConfiguration = ctx.getBean(BlogConfiguration.class);
            assertEquals(postsPerPage, blogConfiguration.getPostsPerPage());
        }
    }

    @DisabledIfEnvironmentVariable(named = "BLOG_POSTS_PER_PAGE", matches = ".*")
    @Test
    void testSystemProperty() {
        final String systemProperty = "blog.posts.per.page";
        String postsPerPageSystemProperty = System.getProperty(systemProperty);
        System.setProperty(systemProperty, "10");
        try (ApplicationContext ctx = ApplicationContext.run()) {
            BlogConfiguration blogConfiguration = ctx.getBean(BlogConfiguration.class);
            assertEquals(10, blogConfiguration.getPostsPerPage());
        }
        if (StringUtils.isNotEmpty(postsPerPageSystemProperty)) {
            System.setProperty(systemProperty, postsPerPageSystemProperty);
        }
    }
}