package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    private Score score;

    @Test
    void canBeCalculatedTest() {
        score = new Score(10,1);
        assertThat(score.canBeCalculated()).isFalse();
    }

    @Test
    void notScoredTest() {
        assertThat(Score.NOT_SCORED.canBeCalculated()).isTrue();
    }
}
