package seul.bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void addScore() {
        Score score = Score.of(0);

        score.addScore(10);
        score.addScore(10);

        assertThat(score.getScore()).isEqualTo(20);
    }

    @Test
    void addBonusScore() {
        Score score = Score.of(0);

        score.addBonusScore(2);

        assertThat(score.getScore()).isEqualTo(2);
    }

    @Test
    void addBonusScoreCount() {
        Score score = Score.of(0);

        score.addBonusScoreCount(2);

        assertThat(score.getBonusScoreCount()).isEqualTo(2);
    }

    @Test
    void addCumulativeScore() {
        Score score = Score.of(0);

        score.addCumulativeScore(21);

        assertThat(score.getScore()).isEqualTo(21);
    }

    @Test
    void of() {
        Score score = Score.of(10);

        assertThat(score.getScore()).isEqualTo(10);
    }
}
