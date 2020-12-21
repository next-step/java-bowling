package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("공 던지기 시 점수 더하기")
    void pitch() {
        Score score = new Score();

        assertThat(score.pitch(3)).isEqualTo(new Score(3, 1));
    }

    @Test
    void isFinished() {
        assertThat(new Score(0, 0).isFinished()).isTrue();
    }
}