package com.micronautbook.essentials;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface BookClient {
    @Post("/books")
    HttpStatus save(@Body Book book);
}
