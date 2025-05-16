package example.micronaut.startup;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceReadyEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ServiceReadyEventListener implements ApplicationEventListener<ServiceReadyEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceReadyEventListener.class);

    @Override
    public void onApplicationEvent(ServiceReadyEvent event) {
        LOG.info("Received {}", event.getClass().getSimpleName());
    }
}
