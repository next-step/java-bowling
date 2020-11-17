package bowling.domain.frame;

import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Frames2Test {
    private Frames2 frames;

    @BeforeEach
    void setUp() {
        frames = Frames2.generate();
    }

    @Test
    void firstRoll() {
        Frames2 firstRoll = frames.roll(Point.of(3));
        assertThat(firstRoll.getSize()).isEqualTo(1);
    }

    @Test
    void secondRoll() {
        Frames2 firstFrame = frames.roll(Point.of(3));
        firstFrame.nextFrame();

        firstFrame.roll(Point.of(3));
        firstFrame.nextFrame();
        assertThat(firstFrame.getSize()).isEqualTo(2);
    }
}
