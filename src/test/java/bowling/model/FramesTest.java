package bowling.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FramesTest {
    @Test
    public void currentPlayingFrame() {
        Frames frames = new Frames();

        for (int i = 0; i < 10; i++) {
            assertThat(frames.isOver()).isFalse();
            frames.record(ShotResult.TEN);
        }
        assertThat(frames.isOver()).isFalse();
    }
}