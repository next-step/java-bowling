package bowlingRefactor;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("남은 투구가 남았는지")
    void leftPitchTest() {
        Score score = Score.ofDefault();
        assertThat(score.isLeft()).isFalse();
    }

    @Test
    @DisplayName("점수 플러스, 보너스 횟수 마이너스")
    void scoreTest() {
        Score strike = Score.ofStrike(0);
        Score spare = Score.ofSpare(0);
        Score miss = Score.of(0, 1);

        assertThat(strike.addBonus(Pin.of(10))).isEqualTo(new Score(1, 20));
        assertThat(spare.addBonus(Pin.of(10))).isEqualTo(new Score(0, 20));
        assertThat(miss.addBonus(Pin.of(10))).isEqualTo(new Score(0, 1));
    }
}
