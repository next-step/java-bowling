package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 점수 구하기")
    void getScore() {
        Strike strike = new Strike();

        Score score = Score.of(10, 0);

        assertThat(strike.getScore()).isEqualTo(score);
    }

    @Test
    @DisplayName("추가 점수 구하기")
    void calculateAdditionalScore() {
        Strike strike = new Strike();

        Score score = Score.of(0, 1);

        assertThat(strike.calculateAdditionalScore(score).getScore()).isEqualTo(10);
    }

}