package example.micronaut;

import io.micronaut.context.env.MapPropertySource;
import io.micronaut.context.env.PropertySource;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.management.endpoint.info.InfoSource;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.time.ZoneId;
import java.util.Map;

@Singleton
class TimeZoneInfoSource implements InfoSource {
    @Override
    public Publisher<PropertySource> getSource() {
        return Publishers.just(
                new MapPropertySource("timeZone",
                        Map.of("timeZone", ZoneId.systemDefault().toString()))
        );
    }
}
