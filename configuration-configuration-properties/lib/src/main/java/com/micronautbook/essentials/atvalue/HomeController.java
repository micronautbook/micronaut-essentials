package com.micronautbook.essentials.atvalue;

import com.micronautbook.essentials.Post;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/atvalue")
class HomeController {
    private static final List<Post> posts = List.of(
            new Post("Post 1"),
            new Post("Post 2"),
            new Post("Post 3"),
            new Post("Post 4"),
            new Post("Post 5"),
            new Post("Post 6"),
            new Post("Post 7"),
            new Post("Post 8"),
            new Post("Post 9"),
            new Post("Post 10")
    );

    private final Integer numberOfPosts;

    HomeController(@Value("${blog.home-posts}") Integer numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    @Get
    List<Post> findAll() {
        return posts.subList(0, numberOfPosts);
    }
}
