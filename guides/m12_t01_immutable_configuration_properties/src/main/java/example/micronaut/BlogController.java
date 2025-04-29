package example.micronaut;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/blog")
public class BlogController {
    private final int max;
    private final BlogRepository blogRepository;

    BlogController(BlogConfiguration blogConfiguration,
                   BlogRepository blogRepository) {
        this.max = blogConfiguration.getPostsPerPage();
        this.blogRepository = blogRepository;
    }

    @Get
    List<Post> posts() {
        return blogRepository.findAll(max);
    }
}
