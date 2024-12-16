package com.micronautbook.essentials;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import java.util.List;

@Controller("/books")
class BookController {

    @Get("/list")
    List<Book> list(@Nullable @QueryValue Integer max) {
        List<Book> l = List.of(
                new Book("Netty in Action", "9781617291470", 263),
                new Book("Head First Java", "9781491910771", 716)
        );
        if (max != null && max < l.size()) {
            l = l.subList(0, max);
        }
        return l;
    }
}
