package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.state.State;
import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstBowlTest {

    @Test
    void 생성() {
        State firstBowl = new Ready().next(PinCount.of(5));
        assertThatThrownBy(() -> firstBowl.getScore()).isInstanceOf(CannotCalculateException.class);
    }

    @Test
    void 종료() {
        FirstBowl firstBowl = new FirstBowl(PinCount.of(5));
        assertThat(firstBowl.isFinish()).isFalse();
    }
}
