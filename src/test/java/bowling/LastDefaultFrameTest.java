package bowling;

import bowling.domain.KnockedPins;
import bowling.domain.frame.LastDefaultFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastDefaultFrameTest {
    @Test
    void convertFirstPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new KnockedPins(5));
        assertThat(lastFrame.convert()).isEqualTo("5|");
    }

    @Test
    void convertStrikeFirstPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new KnockedPins(10));
        assertThat(lastFrame.convert()).isEqualTo("X|");
    }

    @Test
    void convertStrikeSecondPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new KnockedPins(10), new KnockedPins(10));
        assertThat(lastFrame.convert()).isEqualTo("X|X");
    }

    @Test
    void convertSpareSecondPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new KnockedPins(3), new KnockedPins(7));
        assertThat(lastFrame.convert()).isEqualTo("3|/|");
    }

    @Test
    void convertSecondPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new KnockedPins(3), new KnockedPins(4));
        assertThat(lastFrame.convert()).isEqualTo("3|4");
    }

    @Test
    void convertStrikeThirdPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new KnockedPins(10), new KnockedPins(10), new KnockedPins(10));
        assertThat(lastFrame.convert()).isEqualTo("X|X|X");
    }
}
