package example.micronaut;

import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BookControllerTest {
    @Test
    void testList(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String expectedJsonArray = """
                [{"name":"Netty in Action","isbn":"9781617291470","pages":263}]""";
        String json = assertDoesNotThrow(() -> client.retrieve("/books/list"));
        assertEquals(expectedJsonArray, json);
    }

    @Test
    void testShow(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String expectedJson = """
                {"name":"Netty in Action","isbn":"9781617291470","pages":263}""";
        String json = assertDoesNotThrow(() -> client.retrieve("/books/show"));
        assertEquals(expectedJson, json);
    }
}