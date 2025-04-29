package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/books")
class BookController {

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    Book save(@Body Book book) {
        // TODO save book
        return book;
    }
}
