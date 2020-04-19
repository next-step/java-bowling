package seul.bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void addScore() {
        Score score = new Score();

        score.addScore(10, 0);
        score.addScore(10, 0);

        assertThat(score.getScore()).isEqualTo(20);
    }

    @Test
    void addBonusScore() {
        Score score = new Score();

        score.addBonusScore(2);

        assertThat(score.getScore()).isEqualTo(2);
    }

    @Test
    void addCumulativeScore() {
        Score score = new Score();

        score.addCumulativeScore(21);

        assertThat(score.getScore()).isEqualTo(21);
    }
}
