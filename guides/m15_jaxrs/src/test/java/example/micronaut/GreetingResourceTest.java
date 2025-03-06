package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

@MicronautTest
class GreetingResourceTest {

    @Test
    public void testHelloEndpoint(RequestSpecification spec) {
        spec
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from Micronaut"));
    }
}