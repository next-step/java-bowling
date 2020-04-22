package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {
    @Test
    void scoreRange() {
        assertThatCode(() -> Score.of(0))
                .doesNotThrowAnyException();

        assertThatCode(() -> Score.of(10))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> Score.of(11))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Score.of(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getScore() {
        assertThat(Score.of(5).score())
                .isEqualTo(5);
    }

    @Test
    void isMax() {
        assertThat(Score.of(10).isMax())
                .isTrue();

        assertThat(Score.of(9).isMax())
                .isFalse();
    }

    @Test
    void isMin() {
        assertThat(Score.of(0).isMin())
                .isTrue();

        assertThat(Score.of(1).isMin())
                .isFalse();
    }

    @Test
    void isLeftPins() {
        assertThat(Score.of(0).isLeftPins(9))
                .isFalse();

        assertThat(Score.of(1).isLeftPins(9))
                .isTrue();
    }

    @Test
    void isLeftPinsExpectException() {
        assertThatThrownBy(()->Score.of(2).isLeftPins(9))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
