package bowling.domain.shot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PinsTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void scoreRange(int score) {
        assertThatCode(() -> Pins.of(score))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void scoreExpectException(int outOfScoreValue) {
        assertThatThrownBy(() -> Pins.of(outOfScoreValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getScore() {
        assertThat(Pins.of(5).score())
                .isEqualTo(5);
    }

    @Test
    void isMax() {
        assertThat(Pins.of(10).isMax())
                .isTrue();

        assertThat(Pins.of(9).isMax())
                .isFalse();
    }

    @Test
    void isMin() {
        assertThat(Pins.of(0).isMin())
                .isTrue();

        assertThat(Pins.of(1).isMin())
                .isFalse();
    }

    @Test
    void getLeftScore() {
        assertThat(Pins.of(0).getLeftScore())
                .isEqualTo(Pins.of(10));

        assertThat(Pins.of(1).getLeftScore())
                .isEqualTo(Pins.of(9));
    }
}
