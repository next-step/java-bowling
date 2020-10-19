package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    private static final int MAX_FRAME = 10;
    private static final int STRIKE = 10;

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();
    }

    @Test
    void testFrame() {
        assertThat(frames.getFrames().size()).isEqualTo(10);
    }

    private void rolledThird() {
        frames.getNextFrame().roll(new Pin(STRIKE));
        frames.getNextFrame().roll(new Pin(STRIKE));
        frames.getNextFrame().roll(new Pin(STRIKE));
    }

}
