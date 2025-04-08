package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyControllerTest {
    @Test
    void pathVariables() throws IOException {
        executeTest(client -> {
            URI uri = UriBuilder.of("/exchange").path("USD").path("EUR").build();
            String json = assertDoesNotThrow(() -> client.retrieve(uri.toString()));
            assertNotNull(json);
            assertEquals("""
                    {"from":"USD","to":"EUR","exchange":0.91}""", json);
        });
    }

    @Disabled
    @Test
    void pathVariablesDefaultValue() throws IOException {
        executeTest(client -> {
            URI uri = UriBuilder.of("/exchange").build();
            String json = assertDoesNotThrow(() -> client.retrieve(uri.toString()));
            assertNotNull(json);
            assertEquals("""
                {"from":"USD","to":"EUR","exchange":0.91}""", json);
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