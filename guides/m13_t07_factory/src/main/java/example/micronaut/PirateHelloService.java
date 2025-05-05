package example.micronaut;

public class PirateHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "Ahoy";
    }
}
