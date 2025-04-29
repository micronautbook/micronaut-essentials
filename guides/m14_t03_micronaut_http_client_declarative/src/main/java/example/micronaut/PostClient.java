package com.micronautbook.essentials;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client("https://jsonplaceholder.typicode.com")
public interface PostClient {
        @Get("/posts")
        List<Post> fetchPosts();

        @Get("/posts/{id}")
        Post findById(@PathVariable String id);
}
