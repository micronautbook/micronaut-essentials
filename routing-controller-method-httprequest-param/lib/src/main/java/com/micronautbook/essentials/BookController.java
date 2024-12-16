package com.micronautbook.essentials;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/books")
class BookController {

    @Get("/list")
    List<Book> list(HttpRequest<?> request) {
        if (!request.getPath().equals("/books/list")) {
            throw new RuntimeException("http request path is not /books/list");
        }
        return List.of(new Book("Netty in Action", "9781617291470", 263));
    }
}
