package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class UtmControllerTest {

    @Test
    void accessQueryValue() throws IOException {
        executeTest(client -> {
            String uri = UriBuilder.of("/utm")
                    .queryParam("utm_source", "google")
                    .build()
                    .toString();
            assertEquals("/utm?utm_source=google", uri);
            String json = assertDoesNotThrow(() -> client.retrieve(uri));
            assertNotNull(json);
            assertEquals("""
                            {"source":"google"}""", json);
        });
    }

    @Test
    void noQueryValueAnnotation() throws IOException {
        executeTest(client -> {
            String uri = UriBuilder.of("/utm")
                    .path("noann")
                    .queryParam("utm_source", "google")
                    .build()
                    .toString();
            assertEquals("/utm/noann?utm_source=google", uri);
            String json = assertDoesNotThrow(() -> client.retrieve(uri));
            assertNotNull(json);
            assertEquals("""
                            {"source":"google"}""", json);
        });
    }

    @Test
    void queryValueRequired() throws IOException {
        executeTest(client -> {
            String uri = UriBuilder.of("/utm")
                    .build()
                    .toString();
            assertEquals("/utm", uri);
            HttpClientResponseException ex =assertThrows(HttpClientResponseException.class,
                    () -> client.retrieve(uri));
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        });
    }

    private static void executeTest(Consumer<BlockingHttpClient> clientConsumer) throws IOException {
        try (EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class)) {
            try (HttpClient httpClient = server.getApplicationContext().createBean(HttpClient.class, server.getURL())) {
                try (BlockingHttpClient client = httpClient.toBlocking()) {
                    clientConsumer.accept(client);
                }
            }
        }
    }
}