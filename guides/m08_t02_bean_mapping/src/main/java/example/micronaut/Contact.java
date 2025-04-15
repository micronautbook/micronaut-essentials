package example.micronaut;

import java.util.List;

public record Contact(
        String firstName,
        String lastName,
        String jobTitle,
        String company,
        List<String> urls) {
}
