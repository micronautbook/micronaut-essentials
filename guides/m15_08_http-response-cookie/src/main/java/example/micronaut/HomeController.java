package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.cookie.Cookie;

@Controller
class HomeController {
    private static String HTML_HELLO_WORLD = """
                <!DOCTYPE html>
                <html>
                <head>
                <title>Hello World</title>
                </head>
                <body>               
                <h1>Hello World</h1>
                </body>
                </html>
                """;

    @Get
    @Produces(MediaType.TEXT_HTML)
    HttpResponse<String> index() {
        return HttpResponse.ok(HTML_HELLO_WORLD)
                .cookie(Cookie.of("username", "john"))
                ;
    }
}
