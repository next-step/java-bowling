package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @Test
    void frameIndexExceptionTest() {
        int frameIndex = 9;
        assertThatThrownBy(() -> {
            new FinalFrame(frameIndex);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rollingEndTest() {
        int frameIndex = 10;

        FinalFrame finalFrame = new FinalFrame(frameIndex);
        finalFrame.bowl(new Pin(2));
        finalFrame.bowl(new Pin(5));

        assertTrue(finalFrame.rollingEnd());
    }

    @Test
    void canTryExtraRollingTest() {
        int frameIndex = 10;

        FinalFrame finalFrame = new FinalFrame(frameIndex);
        finalFrame.bowl(new Pin(2));
        finalFrame.bowl(new Pin(8));

        assertFalse(finalFrame.rollingEnd());
    }

    @Test
    void isFinishExtraRollingTest() {
        int frameIndex = 10;

        FinalFrame finalFrame = new FinalFrame(frameIndex);
        finalFrame.bowl(new Pin(2));
        finalFrame.bowl(new Pin(8));
        finalFrame.bowl(new Pin(5));

        assertTrue(finalFrame.rollingEnd());
    }
}
