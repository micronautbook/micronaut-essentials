package example.micronaut;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/books")
class BookController {

    @Post
    Book save(@Body Book book) {
        // TODO save book
        return book;
    }
}
