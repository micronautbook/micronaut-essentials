package com.micronautbook.essentials;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactBuilderTest {

    @Test
    void builderTest() {
        var expected = new Contact("Tim", "Cook", "CEO", "Apple Inc.", List.of("https://www.apple.com/leadership/tim-cook/"));
        assertEquals(expected, ContactBuilder.builder()
                .firstName("Tim")
                .lastName("Cook")
                .jobTitle("CEO")
                .company("Apple Inc.")
                .urls(List.of("https://www.apple.com/leadership/tim-cook/"))
                .build());
    }
}
