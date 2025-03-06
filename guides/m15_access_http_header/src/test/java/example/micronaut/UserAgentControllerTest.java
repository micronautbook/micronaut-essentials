package example.micronaut;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class UserAgentControllerTest {

    @Test
    void userAgentEcho(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String userAgent = "IntelliJ HTTP Client/IntelliJ IDEA 2024.3.3";
        HttpRequest<?> request = HttpRequest.GET("/user-agent")
                .accept(MediaType.TEXT_PLAIN)
                .header("User-Agent", userAgent);
        assertEquals(userAgent, client.retrieve(request));

        request = HttpRequest.GET("/user-agent-no-value")
                .accept(MediaType.TEXT_PLAIN)
                .header("User-Agent", userAgent);
        assertEquals(userAgent, client.retrieve(request));
    }
}