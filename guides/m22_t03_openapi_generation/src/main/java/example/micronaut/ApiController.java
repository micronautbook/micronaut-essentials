package example.micronaut;

import example.micronaut.api.DefaultApi;
import example.micronaut.model.Book;
import io.micronaut.http.annotation.Controller;
import jakarta.validation.Valid;

import java.util.List;

@Controller
class ApiController implements DefaultApi {
    private final Book nettyInAction;

    ApiController() {
        nettyInAction = new Book("Netty in Action");
        nettyInAction.setIsbn("9781617291470");
        nettyInAction.setPages(263);
    }

    @Override
    public List<@Valid Book> list() {
        return List.of(nettyInAction);
    }

    @Override
    public Book show() {
        return nettyInAction;
    }
}
