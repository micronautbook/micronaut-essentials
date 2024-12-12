package com.micronautbook.essentials;

import io.micronaut.core.beans.BeanIntrospection;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContactIntrospectionTest {

    @Test
    void loadCsvAndInstantiateObjectsViaBeanIntrospection() throws Exception {
        var resource = getClass().getClassLoader().getResource("apple.csv");
        assertNotNull(resource, "app.csv not found!");
        var path = Path.of(resource.toURI());

        List<String> lines = Files.readAllLines(path);

        var introspection = BeanIntrospection.getIntrospection(Contact.class);

        List<Contact> contacts = lines.stream()
                .map(l -> introspection.instantiate((Object[]) l.split(",")))
                .toList();

        List<Contact> expected = List.of(
                new Contact("Tim Cook", "CEO", "Apple Inc."),
                new Contact("Craig Federighi", "Senior Vice President - Software Engineering", "Apple Inc.")
        );

        assertEquals(expected, contacts);
    }
}
