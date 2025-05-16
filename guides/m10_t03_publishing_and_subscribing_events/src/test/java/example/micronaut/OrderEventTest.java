package example.micronaut;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PublishListenEventsTest {
    @Test
    void publishListenEventsTest() {
        MemoryAppender appender = new MemoryAppender();
        addAppenders(appender);
        appender.start();
        EmbeddedServer server = startServer();

        HttpClient httpClient = server.getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
        BlockingHttpClient client = httpClient.toBlocking();
        HttpResponse<?> response = assertDoesNotThrow(
                () -> client.exchange(HttpRequest.POST("/order", Collections.emptyMap())));
        assertEquals(HttpStatus.CREATED, response.getStatus());

        await().until(() -> appender.getEvents()
                .stream()
                .map(ILoggingEvent::getFormattedMessage)
                .anyMatch(m -> m.contains("Amount: 100")));
        server.close();
        appender.stop();
    }

    private static void addAppenders(MemoryAppender appender) {
        for (Class<?> clazz : Arrays.asList(
                OrderEventListener.class
        )) {
            Logger l = (Logger) LoggerFactory.getLogger(clazz);
            l.addAppender(appender);
        }
    }

    private static EmbeddedServer startServer() {
        return ApplicationContext.run(EmbeddedServer.class,Collections.emptyMap());
    }
}

