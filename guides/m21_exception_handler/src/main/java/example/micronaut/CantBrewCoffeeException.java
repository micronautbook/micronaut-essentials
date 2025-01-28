package example.micronaut;

public class CantBrewCoffeeException extends RuntimeException {
    public CantBrewCoffeeException() {
        super("I'm a teapot, I can't brew coffee!");
    }
}
