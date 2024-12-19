package com.micronautbook.essentials;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static io.micronaut.core.type.Argument.mapOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BookControllerTest {
    @Test
    void cookie(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpResponse<?> response = client.exchange(HttpRequest.POST("/books/chocolate", Collections.emptyMap()));
        assertEquals("chocolate", response.getCookie("yummy_cookie").get().getValue());
        assertEquals("strawberry", response.getCookie("tasty_cookie").get().getValue());

        Argument<Map<String, String>> mapArgument = mapOf(String.class, String.class);
        Map<String, String> m = client.retrieve(HttpRequest.GET("/books/chocolate").cookies(response.getCookies().getAll()), mapArgument);
        assertEquals(Map.of("yummy_cookie", "chocolate", "tasty_cookie", "strawberry"), m);
    }

    @Test
    void testList(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String expectedJson = "{\"name\":\"Netty in Action\",\"isbn\":\"9781617291470\",\"pages\":263}";
        String expectedJsonArray = "[" + expectedJson + "]";
        String json = client.retrieve("/books/list");
        assertEquals(expectedJsonArray, json);
        json = client.retrieve("/books/list/response");
        assertEquals(expectedJsonArray, json);
        json = client.retrieve("/books/list/status");
        assertEquals(expectedJsonArray, json);

        HttpResponse<?> response = client.exchange(HttpRequest.POST("/books/save", expectedJson).header("User-Agent", "Micronaut"));
        assertEquals(HttpStatus.CREATED, response.status());

        response = client.exchange(HttpRequest.POST("/books/save/status", expectedJson));
        assertEquals(HttpStatus.CREATED, response.status());

        response = client.exchange(HttpRequest.POST("/books/save/form", "name=Netty+in+Action&isbn=9781617291470&pages=263")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertEquals(HttpStatus.CREATED, response.status());


    }
}