package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @Test
    void 보너스_점수_더하기() {
        Scores scores = new Scores(new Score(20, 1), new Score(10, 1));
        scores.addBonusScore(new Pin(2));
        assertThat(scores.getScores()).contains(
                new Score(22, 0),
                new Score(34, 0)
        );
    }

    @Test
    void 점수_더하기() {
        Scores scores = new Scores(new Score(20, 0), new Score(37, 0));
        scores.add(new Score(7, 0));
        assertThat(scores.getScores()).contains(new Score(44, 0));
    }
}
