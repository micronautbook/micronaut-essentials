package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BookControllerTest {
    private static final String EXPECTED_JSON = """
                {"name":"Netty in Action","isbn":"9781617291470","pages":263}""";

    @Test
    void testShow(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.POST("/books", EXPECTED_JSON);
        String json = assertDoesNotThrow(() -> client.retrieve(request));
        assertEquals(EXPECTED_JSON, json);
    }
}