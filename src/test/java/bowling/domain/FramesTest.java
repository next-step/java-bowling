package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.*;
import static bowling.domain.Pitch.STRIKE_PIN_NUMBER;
import static bowling.domain.PitchTest.FIVE_PIN_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {

    @Test
    void testFinishedStrikeFrames() {
        Frames fullFrames = ofStrikeFrames(LAST_FRAME);
        fullFrames.pitch(STRIKE_PIN_NUMBER);
        fullFrames.pitch(STRIKE_PIN_NUMBER);
        assertThat(fullFrames.finished()).isTrue();

        Frames nineFrames = ofStrikeFrames(NINTH_FRAME);
        assertThat(nineFrames.finished()).isFalse();
    }

    @Test
    void testFinishedSpareFrames() {
        Frames spareFrames = ofSpareFrames(LAST_FRAME);
        spareFrames.pitch(STRIKE_PIN_NUMBER);
        assertThat(spareFrames.finished()).isTrue();

        Frames nineFrames = ofSpareFrames(NINTH_FRAME);
        assertThat(nineFrames.finished()).isFalse();
    }

    public static Frames ofStrikeFrames(int frameNumber) {
        Frames frames = new Frames();

        IntStream.rangeClosed(FIRST_FRAME, frameNumber)
                .forEach(it -> frames.pitch(STRIKE_PIN_NUMBER));

        return frames;
    }

    public static Frames ofSpareFrames(int frameNumber) {
        Frames frames = new Frames();

        IntStream.rangeClosed(FIRST_FRAME, frameNumber)
                .forEach(it -> {
                    frames.pitch(FIVE_PIN_NUMBER);
                    frames.pitch(FIVE_PIN_NUMBER);
                });

        return frames;
    }
}
