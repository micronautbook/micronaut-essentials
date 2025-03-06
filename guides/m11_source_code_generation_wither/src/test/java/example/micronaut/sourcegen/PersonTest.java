package example.micronaut.sourcegen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    @Test
    void testPerson() {
        Person person = new Person("Fred", 42);
        assertEquals("Fred", person.name());
        assertEquals(42, person.age());

        Person person2 = person.withName("Sue");
        assertEquals("Sue", person2.name());
        assertEquals(42, person2.age());

        Person person3 = person.withAge(43);
        assertEquals("Fred", person3.name());
        assertEquals(43, person3.age());
    }
}