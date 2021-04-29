package step4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("카운트 할 수 없는 점수를 생성한다.")
    void unCountableScore() {
        Score score = Score.unCountableScore();
        assertThat(score.value()).isEqualTo(-1);
    }

    @Test
    @DisplayName("점수를 2번 더할 수 있는 기회를 가지는 점수를 생성한다.")
    void strike() {
        Score strike = Score.Strike();
        assertThat(strike.leftOpportunity()).isEqualTo(2);
    }

    @Test
    @DisplayName("점수를 1번 더할 수 있는 기회를 가지는 점수를 생성한다.")
    void spare() {
        Score spare = Score.Spare();
        assertThat(spare.leftOpportunity()).isEqualTo(1);
    }

    @Test
    @DisplayName("점수를 더할 수 있는 기회가 없는 점수를 생성한다.")
    void miss() {
        Score miss = Score.Miss(10);
        assertThat(miss.leftOpportunity()).isEqualTo(0);
    }

    @Test
    @DisplayName("점수를 더할 수 있는 기회가 남아있을 경우, 참을 반환한다.")
    void isOpportunityLeft() {
        Score strike = Score.Strike();
        Score spare = Score.Spare();
        Score miss = Score.Miss(10);
        Score unCountableScore = Score.unCountableScore();

        assertThat(strike.isOpportunityLeft()).isTrue();
        assertThat(spare.isOpportunityLeft()).isTrue();
        assertThat(miss.isOpportunityLeft()).isFalse();
        assertThat(unCountableScore.isOpportunityLeft()).isFalse();
    }
    
    @Test
    @DisplayName("더할 수 없는 점수일 경우, 참을 반환한다.")
    public void isUnCountable() throws Exception {
        Score unCountableScore = Score.unCountableScore();
        assertThat(unCountableScore.isUnCountable()).isTrue();
    }
}
