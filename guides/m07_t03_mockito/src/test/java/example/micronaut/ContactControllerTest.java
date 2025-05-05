package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@MicronautTest
class ContactControllerTest {
    @Inject
    ContactRepository contactRepository;

    @Test
    void deleteDelegateToRepository(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        Long contactId = 6L;
        URI uri = UriBuilder.of("/contact").path("" + contactId).build();
        HttpRequest<?> req = HttpRequest.DELETE(uri);
        HttpResponse<?> rsp = assertDoesNotThrow(() -> client.exchange(req));
        assertEquals(HttpStatus.NO_CONTENT, rsp.getStatus());
        verify(contactRepository).delete(contactId);
    }

    @MockBean(ContactRepository.class)
    ContactRepository contactRepository() {
        return mock(ContactRepository.class);
    }
}