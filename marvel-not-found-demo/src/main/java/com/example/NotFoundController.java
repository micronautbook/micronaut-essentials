package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Produces;

@Controller
class NotFoundController {

    @Error(status = HttpStatus.NOT_FOUND, global = true)
    @Produces(MediaType.TEXT_HTML)
    HttpResponse<String> notFound() {
        return HttpResponse.notFound("""
                <!DOCTYPE html>
                <html>
                <head>
                <title>Not Found</title>
                </head>
                <body>
                <h1>404 PAGE NOT FOUND</h1>
                <h2>$#&%, you broken something! Just kidding...</h2>
                <p>Check that you typed the address correctly, go back to your previous page or try using our site search to find something specific.</p>                    
                </body>
                </html>
    """);
    }
}
