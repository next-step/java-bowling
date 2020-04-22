package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {
    @ParameterizedTest
    @ValueSource(ints = {0,10})
    void scoreRange(int score) {
        assertThatCode(() -> Score.of(score))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,11})
    void scoreExpectException(int outOfScoreValue) {
        assertThatThrownBy(() -> Score.of(outOfScoreValue))
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
