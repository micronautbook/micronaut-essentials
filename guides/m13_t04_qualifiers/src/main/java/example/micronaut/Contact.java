package example.micronaut;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Contact(String name,
                      String url,
                      String email) {
}
