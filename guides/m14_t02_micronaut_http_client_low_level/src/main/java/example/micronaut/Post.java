package example.micronaut;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Post(
        Long id,
        Long userId,
        String title,
        String body) {
}
