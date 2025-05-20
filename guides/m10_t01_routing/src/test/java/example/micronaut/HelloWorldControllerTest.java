package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@MicronautTest
class HelloWorldControllerTest {

    @Test
    void routing(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.POST("/hello", Collections.emptyMap());
        String rsp = assertDoesNotThrow(() -> client.retrieve(request));
        assertEquals("""
                {"message":"By default, Micronaut answers with application/json content type"}""", rsp);

        HttpRequest<?> formRequest = HttpRequest.POST("/hello", Collections.emptyMap())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
        rsp = assertDoesNotThrow(() -> client.retrieve(formRequest));
        assertEquals("""
                {"message":"You can specify the request's content-type that your method accepts with @Consumes"}""", rsp);
    }
}