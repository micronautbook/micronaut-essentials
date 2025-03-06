package example.micronaut.sourcegen;

import io.micronaut.sourcegen.annotations.Wither;

@Wither
public record Person(String name, int age)
        implements PersonWither {
}
