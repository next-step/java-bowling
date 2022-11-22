package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.Score2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThat(strike.getScore()).isEqualTo(new Score2(10, 2));
    }
}
