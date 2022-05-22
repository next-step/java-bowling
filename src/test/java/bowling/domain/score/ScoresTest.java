package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    @Test
    @DisplayName("프레임이 지날수록 점수가 누적된다")
    void accumulateScore() {
        Scores scores = Scores.create(List.of(Score.strike(), Score.strike(), Score.strike()));
        List<Integer> accumulateScore = scores.accumulateScore();

        assertThat(accumulateScore).containsExactly(10, 20, 30);
    }

}