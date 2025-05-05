package example.micronaut;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class MapContactParserTest {
    @Inject
    Map<String, ContactParser> contactParsers;

    @Inject
    ResourceLoader resourceLoader;

    @Test
    void csvContactParser() throws IOException {
        for (Map.Entry<String, ContactParser> entry : contactParsers.entrySet()) {
            String suffix = entry.getKey().equals("vcard") ? "vcf" : "csv";
            ContactParser contactParser = entry.getValue();
            Optional<InputStream> inputStreamOptional = resourceLoader.getResourceAsStream("classpath:micronaut." + suffix);
            assertTrue(inputStreamOptional.isPresent());
            try (InputStream inputStream = inputStreamOptional.get()) {
                Optional<Contact> contactOptional = contactParser.parse(inputStream);
                assertTrue(contactOptional.isPresent());
                Contact contact = contactOptional.get();
                assertEquals(new Contact("Micronaut Framework", "https://micronaut.io", "info@micronaut.io"), contact);
            }
        }
    }
}
