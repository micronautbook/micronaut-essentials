package example.micronaut;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

import java.util.Map;

@Client(id = "github")
public interface GitHubClient {
    @Get("/users/{username}")
    Map<String, Object> userProfile(@PathVariable String username);
}
