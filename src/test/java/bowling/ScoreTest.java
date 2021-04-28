package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("미스 확인")
    void missTest() {
        Score score = new Score(3, 0);
        assertThat(score).isEqualTo(Score.ofMiss(3));
    }

    @Test
    @DisplayName("스페어 확인")
    void spareTest() {
        Score score = new Score(10, 1);
        assertThat(score).isEqualTo(Score.ofSpare());
    }

    @Test
    @DisplayName("Score bonus 점수 확인")
    void addScoreTest() {
        Score score = new Score(10, 1);
        score.addScore(10);
        assertThat(score).isEqualTo(new Score(20, 0));
    }
}
