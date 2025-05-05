package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class HtmlErrorPageTest {

    @Test
    void beanReplacement(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest req = HttpRequest.GET("/").accept(MediaType.TEXT_HTML);
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () -> client.exchange(req));
        Optional<String> htmlOptional = ex.getResponse().getBody(String.class);
        assertTrue(htmlOptional.isPresent());
        String html = htmlOptional.get();
        assertEquals("""
                <!DOCTYPE html>
                <html>
                <head>
                <title>Error</title>
                </head>
                <body>              
                <h1>Error</h1>
                </body>
                </html>
                """, html);
    }
}
