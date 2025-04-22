package example.micronaut;

import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Singleton
class BookRepositoryImpl implements BookRepository {
    @Override
    public void save(@NotNull @Valid Book book) {

    }
}
