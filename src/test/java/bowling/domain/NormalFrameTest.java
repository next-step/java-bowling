package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame(0);
    }

    @Test
    @DisplayName("다음 normalFrame으로 넘어감")
    void bowl_notNextNormalFrame() {
        Frame frame = normalFrame.bowl(9);

        assertThat(frame).isSameAs(normalFrame);
    }

    @Test
    @DisplayName("다음 normalFrame으로 넘어감")
    void bowl_nextNormalFrame() {
        Frame frame = normalFrame.bowl(10);

        assertThat(frame).isNotSameAs(normalFrame);
    }

    @Test
    @DisplayName("마지막 라운드로 넘어가기")
    void bowl_nextFinalFrame() {
        normalFrame = new NormalFrame(8);
        Frame frame = normalFrame.bowl(10);
        frame = frame.bowl(10);

        assertThat(frame).isInstanceOf(FinalFrame.class);
    }
}