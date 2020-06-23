package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoresTest {

    @DisplayName("첫번째와 두번째 투구의 점수의 합이 10을 넘으면 IllegalArgumentException")
    @Test
    void getScoreTotal() {
        Score firstScore = Score.of(3);
        Score secondScore = Score.of(8);
        Scores scores = Scores.from(firstScore);

        assertThatThrownBy(() -> scores.inputSecondScore(secondScore))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("점수의 합");
    }


}
