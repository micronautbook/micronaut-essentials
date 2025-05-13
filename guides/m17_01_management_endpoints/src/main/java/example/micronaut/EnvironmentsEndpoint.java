package example.micronaut;

import io.micronaut.context.env.Environment;
import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;
import java.util.LinkedHashMap;
import java.util.Map;

@Endpoint(id = "environments", defaultSensitive = false)
class EnvironmentsEndpoint {
    private final Map<String, Object> result;

    EnvironmentsEndpoint(Environment environment) {
        this.result = new LinkedHashMap<>();
        result.put("activeEnvironments", environment.getActiveNames());
    }

    @Read
    Map<String, Object> getEnvironmentInfo() {
        return this.result;
    }
}
