package bowling;

import bowling.domain.Frames;
import bowling.domain.NormalFrame;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    public void get_frame_test() {
        Frames frames = new Frames();
        frames.addFrame(NormalFrame.first());
        assertThat(frames.currentFrame()).isEqualTo(NormalFrame.first());
    }
}
