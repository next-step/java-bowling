package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.*;
import static bowling.domain.Pitch.STRIKE_PIN_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {
    @DisplayName("test finished function of Frames")
    @Test
    void testFinished() {
        Frames fullFrames = of(LAST_FRAME);
        fullFrames.pitch(STRIKE_PIN_NUMBER);
        fullFrames.pitch(STRIKE_PIN_NUMBER);
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
