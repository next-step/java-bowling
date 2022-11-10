package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.status.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void bowl() {
        Frames frames = Frames.init();
        Frame frame = frames.bowl(new Pin(10));
        assertThat(frames.getCurrentFrame().getStatus()).isInstanceOf(Strike.class);
    }

    @Test
    void bowl2() {
        Frames frames = Frames.init();
        Frame frame = frames.bowl(new Pin(10));
        frames = frames.addNextFrame(frame);
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(2);
    }
}
