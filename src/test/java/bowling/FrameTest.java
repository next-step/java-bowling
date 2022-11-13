package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void hitAllBowlingPins() {
        Frame frame = new Frame();
        frame.hitBowlingPin(3);
        assertThat(frame.finishFrame()).isFalse();
    }

    @Test
    void hitTwice() {
        Frame frame = new Frame();
        frame.hitBowlingPin(3);
        frame.hitBowlingPin(5);
        assertThat(frame.finishFrame()).isTrue();
    }
}