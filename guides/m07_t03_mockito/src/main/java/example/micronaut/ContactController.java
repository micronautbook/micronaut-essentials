package example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Status;

@Controller("/contact")
class ContactController {
    private final ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        contactRepository.delete(id);
    }
}
