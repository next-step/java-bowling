package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void create() {
        Name actual = new Name("abc");

        assertThat(actual).isEqualTo(new Name("abc"));
    }

    @Test
    void validate_length() {
        Assertions.assertAll(
                () -> assertThatThrownBy(() -> new Name("ab")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Name("abcd")).isInstanceOf(IllegalArgumentException.class)
        );
    }
}
