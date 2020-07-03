package bowling.domain.frame;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void frame_create_test() {
        Frames frames = Frames.create();

        assertThat(frames.isFinished()).isFalse();
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(0);
    }

}
