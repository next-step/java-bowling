package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @DisplayName("어떤 점수를 입력으로 받아 계산가능하지 여부를 반환")
    @Test
    void canCalculateTest() {
        Score strike = new Score(10, 2);
        Score spare = new Score(10, 1);
        assertThat(new Miss(new Pins(1), new Pins(2)).canCalculate(strike)).isTrue();
        assertThat(new Miss(new Pins(1), new Pins(2)).canCalculate(spare)).isTrue();
    }

    @DisplayName("Miss의 점수를 반환")
    @Test
    void scoreTest() {
        assertThat(new Miss(new Pins(1), new Pins(2)).score())
                .isEqualTo(new Score(3, 0));
    }

    @DisplayName("어떤 점수를 입력받아 Miss Score와 더한 점수를 반환")
    @Test
    void sumBeforeScoreTest() {
        Miss miss = new Miss(new Pins(5), new Pins(4));
        Strike strike = new Strike();
        Spare spare = new Spare(new Pins(3), new Pins(7));
        assertThat(miss.sumBeforeScore(strike.score())).isEqualTo(new Score(19, 0));
        assertThat(miss.sumBeforeScore(spare.score())).isEqualTo(new Score(15, 0));
    }
}