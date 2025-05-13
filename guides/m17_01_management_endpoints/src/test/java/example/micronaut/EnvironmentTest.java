package example.micronaut;

import io.micronaut.context.env.Environment;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class EnvironmentTest {
    @Test
    void environmentEndpoint(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.GET("/env");
        Map<String, Object> json = assertDoesNotThrow(() ->
                client.retrieve(request,
                        Argument.mapOf(String.class, Object.class)));
        assertEquals(List.of(Environment.TEST),
                json.get("activeEnvironments"));
        assertTrue(json.containsKey("packages"));
    }
}
