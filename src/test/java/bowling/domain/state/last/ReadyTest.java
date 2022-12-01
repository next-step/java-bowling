package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-30
 */
class ReadyTest {

    @Test
    void 생상() {
        State state = new LastFrameReady();
        State next = state.next(PinCount.of(10));
        assertThat(next).isInstanceOf(LastFrameFirstBowl.class);
    }
}