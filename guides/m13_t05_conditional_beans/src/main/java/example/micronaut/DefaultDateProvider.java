package example.micronaut;

import jakarta.inject.Singleton;

import java.time.LocalDate;

@Singleton
public class DefaultDateProvider implements DateProvider {
    @Override
    public LocalDate localDate() {
        return LocalDate.now();
    }
}
