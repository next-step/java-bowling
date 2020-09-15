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
        finalFrame.bowl(2);
        finalFrame.bowl(5);

        assertTrue(finalFrame.rollingEnd());
    }

}
