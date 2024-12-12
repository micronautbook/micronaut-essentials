package com.micronautbook.essentials;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.sourcegen.annotations.Builder;

import java.util.List;

@Introspected
@Builder
public record Contact(
        String firstName,
        String lastName,
        String jobTitle,
        String company,
        List<String> urls) {
}
