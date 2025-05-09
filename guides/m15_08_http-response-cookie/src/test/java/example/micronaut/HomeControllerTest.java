package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class HomeControllerTest {

    @Test
    void testCookie(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.GET("/").accept(MediaType.TEXT_HTML);
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(request));
        assertEquals(HttpStatus.OK, response.status());
        Optional<Cookie> cookieOptional = response.getCookie("username");
        assertTrue(cookieOptional.isPresent());
        Cookie cookie = cookieOptional.get();
        assertEquals("john", cookie.getValue());
    }
}