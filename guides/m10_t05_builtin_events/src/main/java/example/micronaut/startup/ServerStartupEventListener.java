package example.micronaut.startup;


import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ServerStartupEventListener implements ApplicationEventListener<ServerStartupEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ServerStartupEventListener.class);

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        LOG.info("Received {}", event.getClass().getSimpleName());
    }
}
