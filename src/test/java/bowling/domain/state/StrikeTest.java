package bowling.domain.state;

import bowling.domain.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    @DisplayName("스트파이크 트리플 계산")
    void calculateByTripleStrike() {
        Strike strike = new Strike();
        Score score = strike.getScore();

        Strike strike1 = new Strike();
        Strike strike2 = new Strike();

        score = strike1.calculateByBeforeScore(score);
        score = strike2.calculateByBeforeScore(score);

        assertThat(score.getScore()).isEqualTo(30);
    }
}
