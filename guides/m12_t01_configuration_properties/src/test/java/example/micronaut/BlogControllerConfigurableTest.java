package example.micronaut;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "blog.posts-per-page", value = "10")
@MicronautTest
class BlogControllerConfigurableTest {
    private static final @NonNull Argument<List<Post>> ARG_LIST_POST = Argument.listOf(Post.class);

    @Test
    void test(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.GET("/blog");
        List<Post> posts = assertDoesNotThrow(() -> client.retrieve(request, ARG_LIST_POST));
        assertEquals(10, posts.size());

    }
}