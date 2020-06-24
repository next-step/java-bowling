package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoresTest {

    @DisplayName("첫번째와 두번째 투구의 점수의 합이 10을 넘으면 IllegalArgumentException")
    @Test
    void inputSecondScore_over10() {
        Score firstScore = Score.of(3);
        Score secondScore = Score.of(8);
        Scores scores = Scores.from(firstScore);

        assertThatThrownBy(() -> scores.inputSecondScore(secondScore))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("점수의 합");
    }

    @DisplayName("두번째 투구의 점수를 입력하지 않은채 결과를 확인하면 IllegalStateException")
    @Test
    void checkResult_withoutSecondScore() {
        Score firstScore = Score.of(1);
        Scores scores = Scores.from(firstScore);

        assertThatThrownBy(scores::checkResult)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("두번째 투구의 점수");
    }

    @DisplayName("첫번째, 두번째 투구의 점수를 모두 입력하면 결과를 확인할 수 있다")
    @Test
    void checkResult() {
        Score firstScore = Score.of(1);
        Score secondScore = Score.of(1);
        Scores scores = Scores.from(firstScore);
        scores.inputSecondScore(secondScore);

        Result result = scores.checkResult();

        assertThat(result).isNotNull();
    }
}
