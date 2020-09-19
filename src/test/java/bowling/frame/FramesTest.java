package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames frames;

    @Test
    @DisplayName("Frames 초기화")
    void init() {
        frames = Frames.start();
        assertThat(frames).isNotNull();
    }

    @Test
    @DisplayName("Frames에 Frame 추가")
    void add() {
        frames = Frames.start();
        Frame frame = NormalFrame.first();
        Frames currentFrame = Frames.of(frames, frame);
        assertThat(currentFrame.getFrameNumber()).isEqualTo(1);
    }
}
