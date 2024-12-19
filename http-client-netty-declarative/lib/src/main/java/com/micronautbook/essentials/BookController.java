package com.micronautbook.essentials;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import jakarta.validation.Valid;

@Controller("/books")
class BookController {
    @Post
    @Status(HttpStatus.ACCEPTED)
    void save(@Body @Valid Book book) {
    }
}
