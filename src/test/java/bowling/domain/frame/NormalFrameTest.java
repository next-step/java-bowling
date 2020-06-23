package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.PinsState;
import bowling.domain.state.ScoreType;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void play_then_strike() {
        Frame normalFrame = Frame.first();
        normalFrame.play(10);

        PinsState expect = new PinsState(Arrays.asList(10), Arrays.asList(ScoreType.STRIKE));

        assertThat(normalFrame.getPinsState()).isEqualTo(expect);
    }

    @DisplayName("strike후에 플레이 하면 예외 발생한다.")
    @Test
    void strike_and_play_then_throw_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.play(10);
        assertThatThrownBy(() -> normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void two_play() {
        Frame normalFrame = Frame.first();

        normalFrame.play(8);
        normalFrame.play(1);

        PinsState expect = new PinsState(Arrays.asList(8, 1), Arrays.asList(ScoreType.MISS));
        assertThat(normalFrame.getPinsState()).isEqualTo(expect);
    }

    @DisplayName("3번의 플레이를 하면 예외가 발생한다.")
    @Test
    void third_play_then_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.play(8);
        normalFrame.play(1);

        assertThatThrownBy(() -> normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }
}
