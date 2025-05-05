package example.micronaut;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Requires(condition = PirateDayCondition.class)
@Named("pirate")
@Singleton
public class PirateHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "Ahoy";
    }
}
