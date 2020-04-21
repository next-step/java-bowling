package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinishedScoreCalculatorTest {
    @DisplayName("점수를 그대로 반환한다.")
    @Test
    void getScore() {
        int score = 9;
        FinishedScoreCalculator finishedScore =
                new FinishedScoreCalculator(score);
        assertThat(finishedScore.calculateScore().getScore()).isEqualTo(score);
    }
}
