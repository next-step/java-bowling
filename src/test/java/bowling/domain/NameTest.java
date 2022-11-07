package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void create() {
        Name actual = new Name("abc");

        assertThat(actual).isEqualTo(new Name("abc"));
    }

    @DisplayName("Name 검증 테스트")
    @Nested
    class NameValidate {
        @Test
        void validate_length() {
            Assertions.assertAll(
                    () -> assertThatThrownBy(() -> new Name("ab")).isInstanceOf(IllegalArgumentException.class),
                    () -> assertThatThrownBy(() -> new Name("abcd")).isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void validate_english() {
            Assertions.assertAll(
                    () -> assertThatThrownBy(() -> new Name("123")).isInstanceOf(IllegalArgumentException.class),
                    () -> assertThatThrownBy(() -> new Name("가나다")).isInstanceOf(IllegalArgumentException.class)
            );
        }
    }
}
