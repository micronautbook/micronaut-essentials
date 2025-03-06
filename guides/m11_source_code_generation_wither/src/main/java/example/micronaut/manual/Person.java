package example.micronaut.manual;

import java.util.Objects;

public record Person(String name, int age) {
    public Person withName(String name) {
        return Objects.equals(this.name, name)
                ? this
                : new Person(name, age);
    }

    public Person withAge(int age) {
        return (this.age == age) ? this : new Person(name, age);
    }
}
