package com.micronautbook.essentials;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client(id = "jsonplaceholder")
public interface PostClient {
        @Get("/posts")
        List<Post> fetchPosts();
}
