package example.micronaut;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/blog")
public class BlogController {
    private final int max;
    private final BlogRepository blogRepository;

    BlogController(@Value("${blog.posts-per-page:5}") Integer max,
                   BlogRepository blogRepository) {
        this.max = max;
        this.blogRepository = blogRepository;
    }

    @Get
    List<Post> posts() {
        return blogRepository.findAll(max);
    }
}
