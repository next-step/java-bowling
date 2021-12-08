package bowling.domain;

import org.junit.jupiter.api.Test;

import static bowling.domain.Pitch.GUTTER_PIN_NUMBER;
import static bowling.domain.Pitch.STRIKE_PIN_NUMBER;
import static bowling.domain.PitchTest.FIVE_PIN_NUMBER;
import static bowling.domain.PitchTest.TWO_PIN_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FinalFrameTest {

    @Test
    void testFrameAfterPitch() {
        Frame frame = new FinalFrame();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();
        assertThat(frame.score()).isEqualTo(new Score(30));

        frame = new FinalFrame();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();
        assertThat(frame.score()).isEqualTo(new Score(20));

        frame = new FinalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();
        assertThat(frame.score()).isEqualTo(new Score(15));

        frame = new FinalFrame();
        frame.frameAfterPitch(GUTTER_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();
        assertThat(frame.score()).isEqualTo(new Score(15));

        frame = new FinalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.continuable()).isTrue();
        frame.frameAfterPitch(TWO_PIN_NUMBER);
        assertThat(frame.continuable()).isFalse();
        assertThat(frame.score()).isEqualTo(new Score(7));
    }

    @Test
    void testState() {
        Frame frame = new FinalFrame();
        assertThat(frame.state()).isEqualTo("");
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X");
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X|X");
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X|X|X");

        frame = new FinalFrame();
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X");
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X|5");
        frame.frameAfterPitch(TWO_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("X|5|2");

        frame = new FinalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5");
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5|/");
        frame.frameAfterPitch(STRIKE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5|/|X");

        frame = new FinalFrame();
        frame.frameAfterPitch(FIVE_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5");
        frame.frameAfterPitch(TWO_PIN_NUMBER);
        assertThat(frame.state()).isEqualTo("5|2");
    }
}
