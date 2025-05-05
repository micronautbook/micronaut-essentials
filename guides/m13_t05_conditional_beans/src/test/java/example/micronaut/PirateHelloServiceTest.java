package example.micronaut;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class PirateHelloServiceTest {
    @Test
    void hello(HelloService helloService) {
        assertEquals("Ahoy", helloService.sayHello());
    }

    @MockBean(DateProvider.class)
    DateProvider pirateDateProvider() {
        return () -> LocalDate.of(LocalDate.now().getYear(), Month.SEPTEMBER, 19);
    }
}