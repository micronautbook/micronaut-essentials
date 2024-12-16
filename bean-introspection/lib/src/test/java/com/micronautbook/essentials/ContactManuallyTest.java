package com.micronautbook.essentials;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContactManuallyTest {

    @DisabledInNativeImage
    @Test
    void loadCsvAndInstantiateObjectsViaBeanIntrospection() throws Exception {
        var resource = getClass().getClassLoader().getResource("apple.csv");
        assertNotNull(resource, "app.csv not found!");
        var path = Path.of(resource.toURI());

        List<String> lines = Files.readAllLines(path);

        List<Contact> contacts = lines.stream()
                .map(line -> {
                    String[] columns = line.split(",");
                    return new Contact(columns[0].trim(), columns[1].trim(), columns[2].trim());
                })
                .toList();

        List<Contact> expected = List.of(
                new Contact("Tim Cook", "CEO", "Apple Inc."),
                new Contact("Craig Federighi", "Senior Vice President - Software Engineering", "Apple Inc.")
        );

        assertEquals(expected, contacts);
    }
}
