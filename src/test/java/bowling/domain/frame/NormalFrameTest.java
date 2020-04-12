package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("Frame은 프레임 번호를 가진다.")
    @Test
    void create() {
        FrameNumber frameNumber = new FrameNumber(1);
        NormalFrame expect = new NormalFrame(frameNumber);

        NormalFrame actual = new NormalFrame(frameNumber);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("Frame은 다음 프레임을 가지고 있다.")
    @Test
    void getNomalFrame() {
        FrameNumber frameNumber = new FrameNumber(1);
        Frame expect = new NormalFrame(frameNumber.increase());

        Frame actual = new NormalFrame(frameNumber);

        assertThat(actual.getNext().get()).isEqualTo(expect);
    }

    @DisplayName("Frame은 마지막 프레임을 가지고 있다.")
    @Test
    void getFinalFrame() {
        FrameNumber frameNumber = new FrameNumber(9);
        Frame expect = new FinalFrame(frameNumber.increase());

        Frame actual = new NormalFrame(frameNumber);

        assertThat(actual.getNext().get()).isEqualTo(expect);
    }
}