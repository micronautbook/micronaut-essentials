package example.micronaut;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.HtmlErrorResponseBodyProvider;
import jakarta.inject.Singleton;

@Replaces(HtmlErrorResponseBodyProvider.class)
@Singleton
class NotFoundErrorResponseBodyProvider implements HtmlErrorResponseBodyProvider {
    @Override
    public @NonNull String body(@NonNull ErrorContext errorContext, @NonNull HttpResponse<?> response) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                <title>Error</title>
                </head>
                <body>
                <h1>Error</h1>               
                </body>
                </html>
                """;
    }
}
