package example.micronaut;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.ClientFilter;
import io.micronaut.http.annotation.RequestFilter;

import java.util.Optional;

@ClientFilter(serviceId = "github")
class GitHubClientUserAgentFilter {
    public static final String USERS = "/users/";

    @RequestFilter
    public void filter(MutableHttpRequest<?> request) {
        parseUserName(request).ifPresent(username ->
                request.header(HttpHeaders.USER_AGENT, username));
    }

    private static Optional<String> parseUserName(HttpRequest<?> request) {
        return parseUserName(request.getPath());
    }

    private static Optional<String> parseUserName(String path) {
        return path.startsWith(USERS)
                ? Optional.of(path.substring(USERS.length()))
                : Optional.empty();
    }
}
