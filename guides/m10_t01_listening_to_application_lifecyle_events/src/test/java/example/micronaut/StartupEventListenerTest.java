package example.micronaut;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.awaitility.Awaitility.await;

class StartupEventListenerTest {
    @Test
    void bootstrapListens() {
        MemoryAppender appender = new MemoryAppender();
        Logger l = (Logger) LoggerFactory.getLogger(StartupEventListener.class);
        l.addAppender(appender);
        appender.start();
        try (ApplicationContext ctx = Micronaut.run()) {
            await().until(() -> appender.getEvents()
                    .stream()
                    .map(ILoggingEvent::getFormattedMessage)
                    .anyMatch(it -> it.equals("Application started")));
        }
        appender.stop();
    }
}
