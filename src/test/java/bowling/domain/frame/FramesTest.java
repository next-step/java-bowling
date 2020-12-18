package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    void frame_create_test() {
        Frames frames = Frames.create();

        assertThat(frames.isFinished()).isFalse();
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(0);
    }
}
