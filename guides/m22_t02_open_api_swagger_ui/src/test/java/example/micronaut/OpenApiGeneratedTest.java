package example.micronaut;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class OpenApiGeneratedTest {

    @Test
    void buildGeneratesOpenApi(ResourceLoader resourceLoader) throws IOException {
        String path = "META-INF/swagger/default-0.0.yml";
        Optional<InputStream> inputStreamOptional = resourceLoader.getResourceAsStream(path);
        assertTrue(inputStreamOptional.isPresent());
        InputStream inputStream = inputStreamOptional.get();
        String yaml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        String expected = """
openapi: 3.0.1
info:
  title: default
  version: "0.0"
paths:
  /books/list:
    get:
      operationId: list
      responses:
        "200":
          description: list 200 response
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: "#/components/schemas/Book"
  /books/show:
    get:
      operationId: show
      responses:
        "200":
          description: show 200 response
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Book"
components:
  schemas:
    Book:
      required:
      - name
      type: object
      properties:
        name:
          minLength: 1
          type: string
        isbn:
          type: string
          nullable: true
        pages:
          minimum: 0
          exclusiveMinimum: true
          type: integer
          format: int32
          nullable: true
""";
        assertEquals(expected, yaml);
    }
}