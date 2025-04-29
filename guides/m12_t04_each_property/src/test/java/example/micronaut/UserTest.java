package example.micronaut;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "users.sdelamo.email", value = "sergio@example.com")
@Property(name = "users.sdelamo.password", value = "password")
@Property(name = "users.sdelamo.roles[0]", value = "ROLE_ADMIN")
@Property(name = "users.sdelamo.roles[1]", value = "ROLE_USER")
@Property(name = "users.tim.email", value = "tim@example.com")
@Property(name = "users.tim.password", value = "password")
@Property(name = "users.tim.roles[0]", value = "ROLE_USER")
@MicronautTest(startApplication = false)
public class UserTest {
    @Inject
    List<User> users;

    @Test
    void users() {
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(2, users.size());
        User sdelamo = expectedSdelamo();
        assertTrue(users.stream().anyMatch(u -> u.equals(sdelamo)));
        User tim = expectedTim();
        assertTrue(users.stream().anyMatch(u -> u.equals(tim)));
    }

    private User expectedTim() {
        User tim = new User("tim");
        tim.setEmail("tim@example.com");
        tim.setPassword("password");
        tim.setRoles(List.of("ROLE_USER"));
        return tim;
    }

    private User expectedSdelamo() {
        User sdelamo = new User("sdelamo");
        sdelamo.setEmail("sergio@example.com");
        sdelamo.setPassword("password");
        sdelamo.setRoles(List.of("ROLE_ADMIN", "ROLE_USER"));
        return sdelamo;
    }
}
