package bowling.domain.frame;

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
    public void playNextFrameImpossibleWhenLastFrame() {
        Frames frames = Frames.init();
        for (int i = 0; i < 11; i++) {
            frames.play(10);
        }

        assertThat(frames.canPlay()).isFalse();
    }
}
