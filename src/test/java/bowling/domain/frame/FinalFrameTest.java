package bowling.domain.frame;

import bowling.domain.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    FinalFrame finalFrame;
    @BeforeEach
    void makeFinalFrame() {
        finalFrame = new FinalFrame(10);
    }
    @Test
    void frameIndexExceptionTest() {
        int frameIndex = 9;
        assertThatThrownBy(() -> {
            new FinalFrame(frameIndex);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rollingEndTest() {
        finalFrame.bowl(new Pin(2));
        finalFrame.bowl(new Pin(5));

        assertTrue(finalFrame.isEndAllFrame());
    }

    @Test
    void canTryExtraRollingTest() {
        finalFrame.bowl(new Pin(2));
        finalFrame.bowl(new Pin(8));

        assertFalse(finalFrame.isEndAllFrame());
    }

    @Test
    void isFinishExtraRollingTest() {
        finalFrame.bowl(new Pin(2));
        finalFrame.bowl(new Pin(8));
        finalFrame.bowl(new Pin(5));

        assertTrue(finalFrame.isEndAllFrame());
    }
}
