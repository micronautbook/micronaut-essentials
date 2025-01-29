package example.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class NotFoundControllerTest {

    public static final @NonNull Argument<String> ARG_HTML = Argument.of(String.class);

    @Test
    void globalNotFound(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class,
                () -> client.retrieve(HttpRequest.GET("/notFound"), ARG_HTML, ARG_HTML));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        Optional<String> htmlOptional =  ex.getResponse().getBody(String.class);
        assertTrue(htmlOptional.isPresent());
        String html =  htmlOptional.get();
        assertTrue(html.contains(", you broken something! Just kidding..."));
    }
}