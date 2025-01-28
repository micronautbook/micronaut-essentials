package example.micronaut;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Serdeable
public record Book(@NotBlank String name,
                   @Nullable String isbn,
                   @Nullable @Positive Integer pages) {
}
