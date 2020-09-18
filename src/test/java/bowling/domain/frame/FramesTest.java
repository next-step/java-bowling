package bowling.domain.frame;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @Test
    void create() {
        Frames frames = new Frames();
        assertThat(frames.currentFrameIndex()).isEqualTo(String.valueOf(Frame.MIN_FRAME_INDEX));
    }

    @Test
    void frameSizeTest() {
        Frames frames = new Frames();
        assertThat(frames.getFrames()).hasSize(1);
    }

    @Test
    void frameFinishTest() {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.play(new Pin(10));
        }
        assertTrue(frames.isEndAllFrame());
    }
}
