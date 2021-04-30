package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    @Test
    @DisplayName("스트라이크")
    void strike() {
        assertThat(State.of(10, true))
                .isEqualTo(State.STRIKE);
    }

    @Test
    @DisplayName("스페어")
    void spare() {
        assertThat(State.of(10, false))
                .isEqualTo(State.SPARE);
    }

    @Test
    @DisplayName("미스")
    void miss() {
        assertThat(State.of(1, false))
                .isEqualTo(State.MISS);
    }

    @Test
    @DisplayName("거터")
    void gutter() {
        assertThat(State.of(0, false))
                .isEqualTo(State.GUTTER);
    }

    @Test
    @DisplayName("그외")
    void other() {
        assertThat(State.of(1, true))
                .isEqualTo(State.OTHER);
    }
}
