package bowling;

import bowling.domain.KnockedPins;
import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.NormalDefaultFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalDefaultFrameTest {


    @Test
    void isSpare() {
        KnockedPins firstKnockedPins = new KnockedPins(3);
        KnockedPins secondKnockedPins = new KnockedPins(7);
        NormalDefaultFrame normalFrame = new NormalDefaultFrame(firstKnockedPins, secondKnockedPins);
        assertTrue(normalFrame.isSpare(firstKnockedPins, secondKnockedPins));
    }

    @Test
    void convertSpare() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new KnockedPins(3));
        normalDefaultFrame.makeScore(new KnockedPins(7), 2);
        assertThat(normalDefaultFrame.convert()).isEqualTo("3|/");
    }

    @Test
    void convertStrike() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new KnockedPins(10));
        assertThat(normalDefaultFrame.convert()).isEqualTo("X");
    }

    @Test
    void convertFirstPitch() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new KnockedPins(3));
        assertThat(normalDefaultFrame.convert()).isEqualTo("3|");
    }

    @Test
    void convert() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new KnockedPins(4));
        normalDefaultFrame.makeScore(new KnockedPins(5), 2);
        assertThat(normalDefaultFrame.convert()).isEqualTo("4|5");
    }

}
