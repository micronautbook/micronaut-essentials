package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(startApplication = false)
class ContactBeanMapperTest {

    @Test
    void beanMapperTest(ContactMapper contactMapper) {
        var expected = new ContactCard("Tim Cook", "Apple Inc.");
        var contact = new Contact("Tim", "Cook", "CEO", "Apple Inc.", List.of("https://www.apple.com/leadership/tim-cook/"));
        var result = contactMapper.contactToContactCard(contact);
        assertEquals(expected, result);
    }
}
