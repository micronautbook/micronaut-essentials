package example.micronaut;

public record Post(
        Long id,
        Long userId,
        String title,
        String body) {
}
