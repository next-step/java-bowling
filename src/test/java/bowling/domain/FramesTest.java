package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.FIRST_FRAME;
import static bowling.controller.BowlingGame.LAST_FRAME;
import static bowling.domain.PinNumber.STRIKE_PIN_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {
    private final int NINTH_FRAME = 9;

    @DisplayName("test finished function of Frames")
    @Test
    void testFinished() {
        Frames fullFrames = of(LAST_FRAME);
        assertThat(fullFrames.finished()).isTrue();

        Frames nineFrames = of(NINTH_FRAME);
        assertThat(nineFrames.finished()).isFalse();
    }

    private Frames of(int frameNumber) {
        Frames frames = new Frames();

        IntStream.rangeClosed(FIRST_FRAME, frameNumber)
                .forEach(it -> frames.pitch(STRIKE_PIN_NUMBER));

        return frames;
    }
}
