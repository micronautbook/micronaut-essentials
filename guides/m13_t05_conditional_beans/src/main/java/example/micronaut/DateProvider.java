package example.micronaut;

import io.micronaut.context.annotation.DefaultImplementation;

import java.time.LocalDate;

@DefaultImplementation(DefaultDateProvider.class)
public interface DateProvider {
    LocalDate localDate();
}
