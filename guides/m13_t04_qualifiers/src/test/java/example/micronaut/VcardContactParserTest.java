package example.micronaut;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Named;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class VcardContactParserTest {
    @Test
    void csvContactParser(@Named("vcard") ContactParser contactParser,
                          ResourceLoader resourceLoader) throws IOException {
        assertTrue(contactParser instanceof VcardContactParser);
        Optional<InputStream> inputStreamOptional = resourceLoader.getResourceAsStream("classpath:micronaut.vcf");
        assertTrue(inputStreamOptional.isPresent());
        try (InputStream inputStream = inputStreamOptional.get()) {
            Optional<Contact> contactOptional = contactParser.parse(inputStream);
            assertTrue(contactOptional.isPresent());
            Contact contact = contactOptional.get();
            assertEquals(new Contact("Micronaut Framework", "https://micronaut.io", "info@micronaut.io"), contact);
        }

    }
}
