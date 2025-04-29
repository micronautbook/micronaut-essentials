package example.micronaut;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class BootstrapTest {
    @Test
    void testBootstrap(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpResponse<?> rsp = assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/assets/bootstrap-5.3.5-dist/css/bootstrap.css")));
        String cacheControl = rsp.getHeaders().get(HttpHeaders.CACHE_CONTROL);
        assertNotNull(cacheControl);
        assertEquals("public, max-age=31536000, immutable", cacheControl);
        assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/assets/bootstrap-5.3.5-dist/css/bootstrap.css.map")));
        assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/assets/bootstrap-5.3.5-dist/css/bootstrap.min.css")));
        assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/assets/bootstrap-5.3.5-dist/css/bootstrap.min.css.map")));
        assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/assets/bootstrap-5.3.5-dist/js/bootstrap.bundle.min.js")));
    }
}
