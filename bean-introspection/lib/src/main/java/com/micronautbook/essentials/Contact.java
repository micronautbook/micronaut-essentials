package com.micronautbook.essentials;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Contact(
        String name,
        String jobTitle,
        String company) {
}
