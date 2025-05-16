package example.micronaut.shutdown;


import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerShutdownEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ServerShutdownEventListener implements ApplicationEventListener<ServerShutdownEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ServerShutdownEventListener.class);

    @Override
    public void onApplicationEvent(ServerShutdownEvent event) {
        LOG.info("Received {}", event.getClass().getSimpleName());
    }
}
