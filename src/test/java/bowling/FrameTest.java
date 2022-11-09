package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void hitAllBowlingPins() {
        Frame frame = new Frame();
        assertThat(frame.hitBowlingPin(3)).isFalse();
        assertThat(frame.hitBowlingPin(7)).isTrue();
    }

    @Test
    void hitTwice() {
        Frame frame = new Frame();
        assertThat(frame.hitBowlingPin(3)).isFalse();
        assertThat(frame.hitBowlingPin(3)).isTrue();
    }
}