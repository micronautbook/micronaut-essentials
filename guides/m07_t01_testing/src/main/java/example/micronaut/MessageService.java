package example.micronaut;

import jakarta.inject.Singleton;

@Singleton
class MessageService {
    String message() {
        return "Hello World";
    }
}
