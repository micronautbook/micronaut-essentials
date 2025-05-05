package example.micronaut;

import io.micronaut.context.annotation.Secondary;
import jakarta.inject.Singleton;

@Secondary
@Singleton
public class DefaultHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "Hello";
    }
}
