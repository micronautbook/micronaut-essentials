package com.micronautbook.essentials.lombok;

import lombok.Builder;

import java.util.List;

@Builder
public record Contact(
        String firstName,
        String lastName,
        String jobTitle,
        String company,
        List<String> urls) {
}
