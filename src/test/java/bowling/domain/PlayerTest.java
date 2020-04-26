package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @Test
    void of() {
        assertThatCode(() -> Player.of("SKT"))
                .doesNotThrowAnyException();
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> Player.of("tester"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
