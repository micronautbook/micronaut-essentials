package example.micronaut.shutdown;


import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.ShutdownEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ShutdownEventListener implements ApplicationEventListener<ShutdownEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ShutdownEventListener.class);

    @Override
    public void onApplicationEvent(ShutdownEvent event) {
        LOG.info("Received {}", event.getClass().getSimpleName());
    }
}
