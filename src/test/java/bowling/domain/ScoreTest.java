package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @Test
    @DisplayName("Score 보너스 점수 계산 확인")
    void calculate() {
        Score score = new Score(10, 2);
        score.addScore(new Pin(5));
        score.addScore(new Pin(5));
        assertThat(score).isEqualTo(new Score(20, 0));
    }
}