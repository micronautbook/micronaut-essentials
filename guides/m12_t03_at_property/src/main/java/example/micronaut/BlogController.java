package example.micronaut;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/blog")
public class BlogController {
    private final int max;
    private final BlogRepository blogRepository;

    BlogController(@Property(name = "blog.posts-per-page") Integer max,
                   BlogRepository blogRepository) {
        this.max = max;
        this.blogRepository = blogRepository;
    }

    @Get
    List<Post> posts() {
        return blogRepository.findAll(max);
    }
}
