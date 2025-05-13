package example.micronaut;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.context.scope.Refreshable;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Property(name = "spec.name", value = "RefreshTest")
@MicronautTest
class RefreshTest {
    @Inject
    BeanContext beanContext;

    @Test
    void refreshEndpoint(@Client("/") HttpClient httpClient) throws InterruptedException {
        BlockingHttpClient client = httpClient.toBlocking();
        LocalDateTime now = beanContext.getBean(NowRefreshable.class).now();
        Thread.sleep(2000);
        HttpRequest<?> request = HttpRequest.POST("/refresh", Collections.emptyMap());
        assertDoesNotThrow(() -> client.retrieve(request));
        await().until(() -> !beanContext.getBean(NowRefreshable.class).now().equals(now));
    }

    @Requires(property = "spec.name", value = "RefreshTest")
    @Refreshable
    static class NowRefreshable {
        private final LocalDateTime now;
        NowRefreshable() {
            this.now = LocalDateTime.now();
        }
        public LocalDateTime now() {
            return now;
        }
    }
}
