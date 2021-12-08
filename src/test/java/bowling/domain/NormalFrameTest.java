package bowling.domain;

import org.junit.jupiter.api.Test;

import static bowling.domain.Pitch.*;
import static bowling.domain.PitchTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalFrameTest {

    @Test
    void testFrameAfterPitch() {
        Frame frame = new NormalFrame();
        assertThat(frame.frameAfterPitch(STRIKE_PIN_NUMBER)).isNotEqualTo(frame);

        frame = new NormalFrame();
        assertThat(frame.frameAfterPitch(GUTTER_PIN_NUMBER)).isEqualTo(frame);
        assertThat(frame.frameAfterPitch(STRIKE_PIN_NUMBER)).isNotEqualTo(frame);

        frame = new NormalFrame();
        assertThat(frame.frameAfterPitch(EIGHT_PIN_NUMBER)).isEqualTo(frame);
        assertThat(frame.frameAfterPitch(TWO_PIN_NUMBER)).isNotEqualTo(frame);

        frame = new NormalFrame();
        assertThat(frame.frameAfterPitch(FIVE_PIN_NUMBER)).isEqualTo(frame);
        assertThat(frame.frameAfterPitch(TWO_PIN_NUMBER)).isNotEqualTo(frame);
    }

    @Test
    void testContinuable() {
        Frame frame = new NormalFrame();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();

        frame = new NormalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();
    }

    @Test
    void testAddPins() {
        Frame frame = new NormalFrame();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.score()).isEqualTo(new Score(10));
        frame.addPins(STRIKE_PIN_NUMBER);
        assertThat(frame.score()).isEqualTo(new Score(20));
        frame.addPins(STRIKE_PIN_NUMBER);
        assertThat(frame.score()).isEqualTo(new Score(30));

        frame = new NormalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.score()).isEqualTo(new Score(5));
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.score()).isEqualTo(new Score(10));
        frame.addPins(STRIKE_PIN_NUMBER);
        assertThat(frame.score()).isEqualTo(new Score(20));
    }

    @Test
    void testState() {
        Frame frame = new NormalFrame();
        assertThat(frame.state()).isEqualTo("");
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X");

        frame = new NormalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5");
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5|/");

        frame = new NormalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5");
        frame.frameAfterPitch(TWO_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5|2");
    }
}
