package example.micronaut.startup;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class StartupEventListener implements ApplicationEventListener<StartupEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(StartupEventListener.class);

    @Override
    public void onApplicationEvent(StartupEvent event) {
        LOG.info("Received {}", event.getClass().getSimpleName());
    }
}
