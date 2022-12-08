package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void 생성() {
        State next = new Ready().next(PinCount.of(10));
        assertThat(next instanceof Strike).isTrue();
        assertThat(next.isFinish()).isTrue();
    }

    @Test
    void 점수() {
        Strike strike = new Strike();
        assertThat(strike.getScore()).isEqualTo(new Score(10, 2));
    }

    @Test
    void 보너스점수계산() {
        Strike strike = new Strike();
        assertThat(strike.calculateBonusScore(new Score(10, 2))).isEqualTo(new Score(20, 1));
    }
}
