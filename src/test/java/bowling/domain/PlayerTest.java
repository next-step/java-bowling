package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
