package example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(startApplication = false)
public class GitHubClientTest {

    @Test
    void profile(GitHubClient gitHubClient) {
        Map<String, Object> m = gitHubClient.userProfile("sdelamo");
        assertEquals("https://sergiodelamo.com", m.get("blog"));
    }
}
