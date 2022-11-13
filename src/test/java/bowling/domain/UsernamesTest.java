package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class UsernamesTest {

    @Test
    void shouldValidateUsernames() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Usernames(new ArrayList<>()));
    }

    @Test
    void shouldReturnCurrentUser() {
        Usernames usernames = new Usernames(List.of(new Username("abc"), new Username("kcs")));

        assertThat(usernames.current()).isEqualTo(new Username("abc"));
    }

    @Test
    void shouldReturnNextUser() {
        Usernames usernames = new Usernames(List.of(new Username("abc"), new Username("kcs")));

        usernames.next();

        assertThat(usernames.current()).isEqualTo(new Username("kcs"));
    }

}
