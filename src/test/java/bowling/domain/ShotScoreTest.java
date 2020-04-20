package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ShotScoreTest {
    @Test
    void of() {
        assertThatCode(() -> ShotScore.of(5))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void ofException(int score) {
        assertThatThrownBy(() -> ShotScore.of(score))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void score() {
        ShotScore shotScore = ShotScore.of(10);
        assertThat(shotScore.score())
                .isEqualTo(Score.of(10));
    }

    @Test
    void next() {
        ShotScore shotScore = ShotScore.of(5);
        assertThat(shotScore.next(5).scoreType())
                .isEqualTo(ScoreType.SPARE);
    }

    @Test
    void nextException() {
        ShotScore shotScore = ShotScore.of(5);
        assertThatThrownBy(() -> shotScore.next(6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isClear() {
        ShotScore shotScore = ShotScore.of(10);
        assertThat(shotScore.isClear())
                .isTrue();

        shotScore = ShotScore.of(4);
        assertThat(shotScore.next(6).isClear())
                .isTrue();
    }
}
