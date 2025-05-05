package example.micronaut;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Factory
class HelloServiceFactory {
    @Named("pirate")
    @Singleton
    HelloService helloService() {
        return new PirateHelloService();
    }
}
