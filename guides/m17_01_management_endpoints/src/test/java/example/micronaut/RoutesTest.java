package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class RoutesTest {
    @Test
    void routesEndpoint(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.GET("/routes");
        String json = assertDoesNotThrow(() -> client.retrieve(request));
        assertNotNull(json);
        assertTrue(json.contains("/health"));
        assertTrue(json.contains("/routes"));
        assertTrue(json.contains("/beans"));
        assertTrue(json.contains("/info"));
        assertTrue(json.contains("/refresh"));
    }
}
