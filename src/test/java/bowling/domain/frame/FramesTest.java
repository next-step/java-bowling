package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    public void framesInit() {
        Frames frames = Frames.init();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    public void playNextFramePossibleWhenFirstFrame() {
        Frames frames = Frames.init();

        assertThat(frames.canPlay()).isTrue();
    }

    @Test
    public void playNextFrameImpossibleWhenLastFrameStrike() {
        Frames frames = Frames.init();
        for (int i = 0; i < 11; i++) {
            frames.play(10);
        }

        assertThat(frames.canPlay()).isFalse();
    }

    @Test
    public void playNextFrameImpossibleWhenLastFrameMiss() {
        Frames frames = Frames.init();
        for (int i = 0; i < 10; i++) {
            frames.play(10);
        }
        frames.play(8);

        assertThat(frames.canPlay()).isFalse();
    }
}
