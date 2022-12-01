package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by seungwoo.song on 2022-11-30
 */
class LastFrameSecondBowlTest {

    @Test
    void 다음생성() {
        State state = new LastFrameSecondBowl(10, 10);
        assertThat(state.next(PinCount.of(10))).isInstanceOf(LastFrameLastBowl.class);
    }
}