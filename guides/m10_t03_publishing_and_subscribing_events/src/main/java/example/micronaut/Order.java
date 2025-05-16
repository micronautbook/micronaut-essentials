package example.micronaut;

import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Introspected
public record Order(BigDecimal amount, LocalDateTime dateCreated) {
}
