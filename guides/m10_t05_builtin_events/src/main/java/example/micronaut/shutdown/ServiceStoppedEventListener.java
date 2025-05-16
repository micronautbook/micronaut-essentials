package example.micronaut.shutdown;


import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceStoppedEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ServiceStoppedEventListener implements ApplicationEventListener<ServiceStoppedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceStoppedEventListener.class);

    @Override
    public void onApplicationEvent(ServiceStoppedEvent event) {
        LOG.info("Received {}", event.getClass().getSimpleName());
    }
}
