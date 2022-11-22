package bowling.domain.state;

import bowling.domain.PinCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FirstBowlTest {

    @Test
    void 생성() {
        State firstBowl = new Ready().next(PinCount.of(5));
        assertThatIllegalStateException().isThrownBy(() -> firstBowl.getScore());
    }

    @Test
    void 종료() {
        FirstBowl firstBowl = new FirstBowl(PinCount.of(5));
        assertThat(firstBowl.isFinish()).isFalse();
    }
}
