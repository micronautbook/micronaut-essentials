package com.micronautbook.essentials;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/books")
class BookResource {

    @GET
    @Path("/list")
    public List<Book> list() {
        return List.of(new Book("Netty in Action", "9781617291470", 263));
    }
}
