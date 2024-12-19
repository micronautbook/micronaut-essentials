package com.micronautbook.essentials;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.http.util.HttpHeadersUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller("/books")
class BookController {
    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Get("/list")
    List<Book> list(HttpRequest<?> request) {
        HttpHeadersUtil.trace(LOG, request.getHeaders());
        return List.of(new Book("Netty in Action", "9781617291470", 263));
    }

    @Get("/list/response")
    HttpResponse<List<Book>> listResponse() {
        return HttpResponse.ok(List.of(
                new Book("Netty in Action", "9781617291470", 263)
        ));
    }

    @Get("/list/status")
    HttpResponse<List<Book>> listStatus() {
        return HttpResponse.status(HttpStatus.OK).body(List.of(
                new Book("Netty in Action", "9781617291470", 263)
        ));
    }

    @Post("/save")
    @Status(HttpStatus.CREATED)
    void save(@Body Book book,
              @Nullable @Header("User-Agent") String userAgent) {
        // persist the book
        String foo = "";
    }

    @Get("/chocolate")
    @Status(HttpStatus.OK)
    Map<String, Object> chocolate(@CookieValue("yummy_cookie") String yummy,
                                  @CookieValue("tasty_cookie") String tasty) {
        return Map.of("yummy_cookie", yummy, "tasty_cookie", tasty);
    }

    @Post("/chocolate")
    HttpResponse<?> chocolate() {
        return HttpResponse.ok()
                .cookie(Cookie.of("yummy_cookie", "chocolate"))
                .cookie(Cookie.of("tasty_cookie", "strawberry"));
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/save/form")
    @Status(HttpStatus.CREATED)
    void saveForm(@Body Book book) {
        // persist the book
    }

    @Post("/save/status")
    HttpStatus saveStatus(@Body Book book) {
        // persist the bookr
        return HttpStatus.CREATED;
    }
}
