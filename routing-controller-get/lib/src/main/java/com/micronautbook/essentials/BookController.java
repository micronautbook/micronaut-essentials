package com.micronautbook.essentials;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/books")
class BookController {

    @Get("/list")
    List<Book> list() {
        return List.of(new Book("Netty in Action", "9781617291470", 263));
    }
}
