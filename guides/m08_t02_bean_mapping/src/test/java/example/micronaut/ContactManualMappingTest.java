package example.micronaut;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactManualMappingTest {

    @Test
    void beanMapperTest() {
        var expected = new ContactCard("Tim Cook", "Apple Inc.");
        var contact = new Contact("Tim", "Cook", "CEO", "Apple Inc.", List.of("https://www.apple.com/leadership/tim-cook/"));
        var actual = contactToContactCard(contact);
        assertEquals(expected, actual);
    }

    private static ContactCard contactToContactCard(Contact c) {
        return new ContactCard(String.join(" ", c.firstName(), c.lastName()), c.company());
    }
}
