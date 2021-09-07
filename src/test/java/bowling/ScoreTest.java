package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    void onFrameMaxScoreTest() {

        assertThatThrownBy(
                () -> Score.of(31)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void onFrameMinScoreTest() {

        assertThatThrownBy(
                () -> Score.of(-1)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkScore() {
        Score score = Score.of(5);

        assertThat(score.score()).isEqualTo(5);
    }

    @Test
    void addCheckScore() {
        Score score = Score.of(5).add(3);

        assertThat(score.score()).isEqualTo(8);
    }
}
