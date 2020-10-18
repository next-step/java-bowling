package bowling.frame;

import bowling.frame.state.Miss;
import bowling.frame.state.Next;
import bowling.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임 생성")
    void first() {
        Frame first = NormalFrame.first();
        assertThat(first.getFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("첫번째 프레임 볼링 진행 - Strike")
    void firstFrameBowl() {
        Frame first = NormalFrame.first();
        first.bowl("10");
        assertThat(first.getState().isFinish()).isTrue();
        assertThat(first.getState() instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("첫번째 프레임 볼링 진행 - Next")
    void firstFrameBowlisMiss() {
        Frame first = NormalFrame.first();
        first.bowl("3");
        assertThat(first.getState() instanceof Next).isTrue();
    }

    @Test
    @DisplayName("첫번째 프레임 종료 후 다음 프레임으로 이동")
    void next() {
        Frame first = NormalFrame.first();
        first.bowl("10");
        assertThat(first.next().getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("9번 프레임이 진행되면 FinalFrame 생성")
    void createFinalFrame() {
        Frame frame = NormalFrame.create(9);
        assertThat(frame.next().getFrameNumber()).isEqualTo(10);
        assertThat(frame.next() instanceof FinalFrame).isTrue();
    }
}
