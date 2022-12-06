package bowling.domain.state.last;

import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.State;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-30
 */
class LastFrameLastBowlTest {

    @Test
    void 생성() {
        State state = new LastFrameBonus(10, 10, 10);
        assertThat(state.getScore().getValue()).isEqualTo(30);
        assertThat(state.getDesc()).isEqualTo("X|X|X");
    }

    @Test
    void 노말에서_생성() {
        assertThat(new NormalFrame(9).bowl(10)).isInstanceOf(LastFrame.class);
    }

    @Test
    void 스페어_desc() {
        State state = new LastFrameBonus(4, 6, 10);
        assertThat(state.getDesc()).isEqualTo("4|/|X");

        State state2 = new LastFrameBonus(4, 6, 0);
        assertThat(state2.getDesc()).isEqualTo("4|/|-");
    }
}