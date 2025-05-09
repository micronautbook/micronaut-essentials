package example.micronaut;

import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;

@Introspected
public record Person(@NotBlank String name) {
}
