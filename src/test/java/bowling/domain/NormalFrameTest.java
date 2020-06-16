package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.FrameResult;
import bowling.domain.NormalFrame;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {


    @Test
    void play_then_strike() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.play(10);

        FrameResult frameResult = new FrameResult(Arrays.asList(10));

        assertThat(normalFrame.getResult()).isEqualTo(frameResult);
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

        FrameResult frameResult = new FrameResult(Arrays.asList(8, 1));

        assertThat(normalFrame.getResult()).isEqualTo(frameResult);
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
