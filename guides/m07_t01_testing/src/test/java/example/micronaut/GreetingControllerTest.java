package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingControllerTest {

    @Test
    void applicationContextWithoutMicronautTest() {
        try (EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class)) {
            try (HttpClient httpClient = server.getApplicationContext()
                    .createBean(HttpClient.class, server.getURL())) {
                BlockingHttpClient client = httpClient.toBlocking();
                String json = assertDoesNotThrow(() -> client.retrieve("/"));
                assertEquals("""
                        {"message":"Hello World"}""", json);
            }
        }
    }
}