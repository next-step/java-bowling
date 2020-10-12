package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @DisplayName("Score 생성")
    @Test
    void create() {
        Score score = new Score(10, 2);
        assertThat(score).isEqualTo(new Score(10, 2));
    }

    @DisplayName("Score 보너스 점수 계산")
    @Test
    void addScore() {
        Score score = new Score(10, 2);
        score.addScore(10);
        score.addScore(10);

        assertThat(score).isEqualTo(new Score(30, 0));
    }
}