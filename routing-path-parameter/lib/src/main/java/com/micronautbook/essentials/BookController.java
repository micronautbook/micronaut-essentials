package com.micronautbook.essentials;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/books")
class BookController {

    @Get("/{isbn}")
    Book list(String isbn) {
        if (isbn.equals("9781617291470")) {
            return new Book("Netty in Action", "9781617291470", 263);
        }
        return null;
    }
}
