package bowling.domain.engine.frame;

import bowling.domain.concrete.frame.FinalFrame;
import bowling.domain.concrete.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class FrameCreatorTest {

    @Test
    @DisplayName("프레임 셋을 생성한다.")
    void createFrames() {
        LinkedList<Frame> frames = FrameCreator.createFrames();

        for (int i = 0; i < 9; i++) {
            assertThat(frames.get(i)).isInstanceOf(NormalFrame.class);
        }

        assertThat(frames.getLast()).isInstanceOf(FinalFrame.class);
    }

}
