package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {


    @Test
    void play_then_strike() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.play(10);

        List<FrameBowlState> expect = Arrays.asList(new FrameBowlState(10, ScoreType.STRIKE));

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
            new FrameBowlState(8, ScoreType.MISS),
            new FrameBowlState(1, ScoreType.MISS)
        );

        assertThat(normalFrame.getBowlStates()).isEqualTo(expect);
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
