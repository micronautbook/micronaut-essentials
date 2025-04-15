package example.micronaut;

import io.micronaut.core.annotation.Introspected;

@Introspected(classes = {Contact.class, ContactCard.class})
class ContactIntrospection {
}
