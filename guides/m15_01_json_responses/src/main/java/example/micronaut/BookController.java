package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;

@Controller("/books")
class BookController {
    public static final Book NETTY_IN_ACTION =
            new Book("Netty in Action", "9781617291470", 263);

    @Get("/show")
    Book show() {
        return NETTY_IN_ACTION;
    }

    @Get("/list")
    List<Book> list() {
        return List.of(NETTY_IN_ACTION);
    }

    @Get("/list/response")
    HttpResponse<List<Book>> listResponse() {
        return HttpResponse.ok(List.of(NETTY_IN_ACTION));
    }

    @Get("/list/status")
    HttpResponse<List<Book>> listStatus() {
        return HttpResponse.status(HttpStatus.OK).body(List.of(NETTY_IN_ACTION));
    }
}
