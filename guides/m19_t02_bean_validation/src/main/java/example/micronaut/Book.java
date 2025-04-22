package example.micronaut;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Introspected
public record Book(@NotBlank String name,
                   @Nullable String isbn,
                   @Nullable @Positive Integer pages) {
}
