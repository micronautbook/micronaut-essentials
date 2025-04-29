package example.micronaut;

import jakarta.inject.Singleton;
import java.util.List;

@Singleton
public class BlogRepository {
    List<Post> posts = List.of(
            new Post("Micronaut Framework 4.8.2 Released!"),
            new Post("Micronaut Framework 4.8.0 Released!"),
            new Post("Micronaut Framework 4.7.6 Released!"),
            new Post("Micronaut Framework 4.7.5 Released!"),
            new Post("Improving API performance at Sonar with Lambda SnapStart and Micronaut"),
            new Post("Micronaut framework at Kestra"),
            new Post("Agorapulse Micronaut Journey"),
            new Post("Micronaut Framework 4.7.4 Released!"),
            new Post("Micronaut Framework 4.7.3 Released!"),
            new Post("Micronaut Framework 4.7.2 Released!"),
            new Post("Micronaut Framework 4.7.1 Released!")
    );
    public List<Post> findAll(int max) {
        return posts.subList(0, Math.min(max, posts.size()));
    }
}
