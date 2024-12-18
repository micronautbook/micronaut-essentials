package com.micronautbook.essentials;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller
class HomeController {
    private final static List<Post> posts = List.of(
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

    HomeController(BlogConfiguration blogConfiguration) {
        this.numberOfPosts = blogConfiguration.getHomePosts();
    }

    @Get
    List<Post> findAll() {
        return posts.subList(0, numberOfPosts);
    }
}
