package example.micronaut;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import example.micronaut.shutdown.ServerShutdownEventListener;
import example.micronaut.shutdown.ServiceStoppedEventListener;
import example.micronaut.shutdown.ShutdownEventListener;
import example.micronaut.startup.ServerStartupEventListener;
import example.micronaut.startup.ServiceReadyEventListener;
import example.micronaut.startup.StartupEventListener;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuiltInEventsTest {
    @Test
    void builtInEvents() {
        MemoryAppender appender = new MemoryAppender();
        addAppenders(appender);
        appender.start();
        EmbeddedServer server = startServer();
        await().until(() -> existsLogMessageWithName(appender, "Received ServiceReadyEvent"));
        stopServer(server);
        await().until(() -> existsLogMessageWithName(appender, "Received ServiceStoppedEvent"));
        List<String> messages = appender.getEvents().stream().map(ILoggingEvent::getFormattedMessage).toList();
        assertEquals(List.of(
                "Received StartupEvent",
                "Received ServerStartupEvent",
                "Received ServiceReadyEvent",
                "Received ShutdownEvent",
                "Received ServerShutdownEvent",
                "Received ServiceStoppedEvent"
                ), messages);
        server.close();
        appender.stop();
    }

    private static void addAppenders(MemoryAppender appender) {
        for (Class<?> clazz : Arrays.asList(
                ServerShutdownEventListener.class,
                ServerStartupEventListener.class,
                ServiceReadyEventListener.class,
                ServiceStoppedEventListener.class,
                ShutdownEventListener.class,
                StartupEventListener.class
        )) {
            Logger l = (Logger) LoggerFactory.getLogger(clazz);
            l.addAppender(appender);
        }
    }

    private static EmbeddedServer startServer() {
        return ApplicationContext.run(EmbeddedServer.class, Map.of(
                "endpoints.stop.enabled", StringUtils.TRUE,
                "endpoints.stop.sensitive", StringUtils.FALSE
        ));
    }

    private static void stopServer(EmbeddedServer server) {
        HttpClient httpClient = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
        BlockingHttpClient client = httpClient.toBlocking();
        assertDoesNotThrow(() -> client.exchange(HttpRequest.POST("/stop", Collections.emptyMap())));
    }

    private static boolean existsLogMessageWithName(MemoryAppender appender, String message) {
        return appender.getEvents()
                .stream()
                .map(ILoggingEvent::getFormattedMessage)
                .anyMatch(it -> it.equals(message));
    }
}

