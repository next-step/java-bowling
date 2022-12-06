package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-30
 */
class LastFrameSecondBowlTest {

    @Test
    void 다음생성() {
        State state = new LastFrameSecondBowl(10, 10);
        assertThat(state.next(PinCount.of(10))).isInstanceOf(LastFrameBonus.class);
        assertThat(state.getDesc()).isEqualTo("X|X");
    }

    @Test
    void 스페어() {
        State state = new LastFrameSecondBowl(5,5);
        assertThat(state.getDesc()).isEqualTo("5|/");
    }

    @Test
    void 미스() {
        State state = new LastFrameSecondBowl(2,5);
        assertThat(state.getDesc()).isEqualTo("2|5");
    }
}