package bowling.domain.frame;

import bowling.domian.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("strike/spare 추가 계산 필요 확인")
    public void needAdditionalCalculate() {
        Score strikeScore = Score.strike();
        Score spareScore = Score.spare();

        assertThat(strikeScore.isCalculateDone()).isFalse();
        assertThat(spareScore.isCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("miss 추가 계산 불필요 확인")
    public void calculateDone() {
        int falledPinCount = 4;
        Score score = Score.miss(falledPinCount);

        assertThat(score.isCalculateDone()).isTrue();
        assertThat(score.getScore()).isEqualTo(falledPinCount);
    }

    @Test
    @DisplayName("추가 계산 완료 후 상태 확인")
    public void calculateAdditional() {
        Score score = Score.strike();

        assertThat(score.additionalBowl(10).additionalBowl(10).getScore()).isEqualTo(30);
    }
}
