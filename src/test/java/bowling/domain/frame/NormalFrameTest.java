package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.FrameBowlState;
import bowling.domain.state.FrameBowlStates;
import bowling.domain.state.ScoreType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void play_then_strike() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.play(10);

        FrameBowlStates expect = new FrameBowlStates(Arrays.asList(new FrameBowlState(10, ScoreType.STRIKE)));

        assertThat(normalFrame.getBowlStates()).isEqualTo(expect);
    }

    @DisplayName("strike후에 플레이 하면 예외 발생한다.")
    @Test
    void strike_and_play_then_throw_exception() {
        NormalFrame normalFrame = NormalFrame.first();

        normalFrame.play(10);
        assertThatThrownBy(() -> normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void two_play() {
        NormalFrame normalFrame = NormalFrame.first();

        normalFrame.play(8);
        normalFrame.play(1);

        List<FrameBowlState> expect = Arrays.asList(
            new FrameBowlState(8, ScoreType.NUMS),
            new FrameBowlState(1, ScoreType.NUMS)
        );

        assertThat(normalFrame.getBowlStates()).isEqualTo(new FrameBowlStates(expect));
    }

    @DisplayName("3번의 플레이를 하면 예외가 발생한다.")
    @Test
    void third_play_then_exception() {
        NormalFrame normalFrame = NormalFrame.first();

        normalFrame.play(8);
        normalFrame.play(1);

        assertThatThrownBy(() -> normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }
}
