package bowling.frame;

import bowling.frame.state.Next;
import bowling.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Frame frame;

    @Test
    @DisplayName("첫번째 프레임 생성")
    void first() {
        frame = NormalFrame.first();
        assertThat(frame.getFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("첫번째 프레임 볼링 진행 - Strike")
    void firstFrameBowlIsStrike() {
        frame = NormalFrame.first();
        frame.bowl("10");
        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.getState() instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("첫번째 프레임 볼링 진행 - Next")
    void firstFrameBowlIsMissAndNext() {
        frame = NormalFrame.first();
        frame.bowl("3");
        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.getState() instanceof Next).isTrue();
    }

}
