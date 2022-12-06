package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-30
 */
class LastFrameFirstBowlTest {

    @Test
    void 스트라이크() {
        State state = new LastFrameFirstBowl(10);
        assertThat(state.next(PinCount.of(10))).isInstanceOf(LastFrameSecondBowl.class);
        assertThat(state.getDesc()).isEqualTo("X");
    }
}