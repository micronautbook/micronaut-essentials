package com.micronautbook.essentials;

import io.micronaut.context.exceptions.ConfigurationException;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Singleton
public class PostClient {
    private static final String PATH_POSTS = "/posts";
    public static final HttpRequest<?> GET_POSTS = HttpRequest.GET(PATH_POSTS);
    public static final Argument<List<Post>> ARG_LIST_POSTS = Argument.listOf(Post.class);
    private  final BlockingHttpClient client;

    public PostClient() {
            String uri = "https://jsonplaceholder.typicode.com";
            try {
                this.client = HttpClient.create(new URL(uri)).toBlocking();
            } catch (MalformedURLException e) {
                throw new ConfigurationException("malformed URL Exception for " + uri);
            }
        }

        public List<Post> fetchPosts() {
            return client.retrieve(GET_POSTS, ARG_LIST_POSTS);
        }
}
