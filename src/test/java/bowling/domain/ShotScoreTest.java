package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ShotScoreTest {
    @Test
    void of() {
        assertThatCode(() -> ShotScore.init(5))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void ofException(int score) {
        assertThatThrownBy(() -> ShotScore.init(score))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void singleScore() {
        ShotScore shotScore = ShotScore.init(10);
        assertThat(shotScore.singleScore())
                .isEqualTo(10);
    }

    @Test
    void totalScore() {
        ShotScore shotScore = ShotScore.init(10);
        assertThat(shotScore.totalScore())
                .isEqualTo(null);

        shotScore.next(10).next(10);
        assertThat(shotScore.totalScore())
                .isEqualTo(30);

        shotScore = ShotScore.init(4);
        shotScore = shotScore.next(6);
        assertThat(shotScore.totalScore())
                .isEqualTo(null);

        shotScore.next(5);
        assertThat(shotScore.totalScore())
                .isEqualTo(11);
    }

    @Test
    void next() {
        ShotScore shotScore = ShotScore.init(5);
        assertThat(shotScore.next(5).scoreType())
                .isEqualTo(ScoreType.SPARE);
    }

    @Test
    void nextException() {
        ShotScore shotScore = ShotScore.init(5);
        assertThatThrownBy(() -> shotScore.next(6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isClear() {
        ShotScore shotScore = ShotScore.init(10);
        assertThat(shotScore.isClear())
                .isTrue();

        shotScore = ShotScore.init(4);
        assertThat(shotScore.next(6).isClear())
                .isTrue();
    }
}
