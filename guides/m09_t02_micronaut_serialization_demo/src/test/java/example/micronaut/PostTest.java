package example.micronaut;

import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class PostTest {

    @Test
    void deserializeFromJson(JsonMapper jsonMapper) throws IOException {
        var json = """
                {
                    "userId": 3,
                    "id": 1,
                    "title": "sunt aut facere",
                    "body": "quia et suscipit"
                  }
                """;
        var post = jsonMapper.readValue(json, Post.class);
        assertNotNull(post);
        assertEquals(1, post.id());
        assertEquals(3, post.userId());
        assertEquals("sunt aut facere", post.title());
        assertEquals("quia et suscipit", post.body());
    }

    @Test
    void serializeToJson(JsonMapper jsonMapper) throws IOException, JSONException {

        Post post = new Post(1L, 3L, "sunt aut facere","quia et suscipit");
        var expected = """
                {
                    "userId": 3,
                    "id": 1,
                    "title": "sunt aut facere",
                    "body": "quia et suscipit"
                  }
                """;
        var json = jsonMapper.writeValueAsString(post);
        JSONAssert.assertEquals(
                expected, json, JSONCompareMode.LENIENT);
    }
}