package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(firstFrames.size()).isEqualTo(1);
    }

    @Test
    void framesNextPitchTest() {
        Frames frames = Frames.create();
        Frames firstFrames = frames.pitch(Point.inputPoint(5));
        firstFrames.next();

        assertThat(firstFrames.size()).isEqualTo(1);

        firstFrames.pitch(Point.inputPoint(3));
        firstFrames.next();

        assertThat(firstFrames.size()).isEqualTo(2);
    }
}
