package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreRullTest {
    @Test
    void strike() {
        assertThat(ScoreRull.of(10, true)).isEqualTo(ScoreRull.STRIKE);
    }

    @Test
    void spare() {
        assertThat(ScoreRull.of(10, false)).isEqualTo(ScoreRull.SPARE);
    }

    @Test
    void gutter() {
        assertThat(ScoreRull.of(0, false)).isEqualTo(ScoreRull.GUTTER);
    }

    @ParameterizedTest(name = "miss test - args : [{arguments}]")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void gutter(int score) {
        assertThat(ScoreRull.of(score, false)).isEqualTo(ScoreRull.MISS);
    }
}
