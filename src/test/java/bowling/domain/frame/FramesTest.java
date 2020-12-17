package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    void createFramesTest() {
        Frames frames = Frames.create();
        assertThat(frames).isNotNull();
    }

    @Test
    void framesPitchTest() {
        Frames frames = Frames.create();
        Frames firstFrames = frames.pitch(Point.inputPoint(5));
        assertThat(firstFrames.frameOfIndex(0).getScores()).isEqualTo("5");
    }
}
