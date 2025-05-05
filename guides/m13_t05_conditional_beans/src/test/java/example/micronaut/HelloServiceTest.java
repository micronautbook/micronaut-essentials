package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Named;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class HelloServiceTest {
    @Test
    void hello(HelloService helloService) {
        assertEquals("Hello", helloService.sayHello());
    }
}