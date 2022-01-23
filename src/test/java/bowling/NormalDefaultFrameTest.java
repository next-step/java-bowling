package bowling;

import bowling.domain.Score;
import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.NormalDefaultFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalDefaultFrameTest {


    @Test
    void isSpare() {
        Score firstScore = new Score(3);
        Score secondScore = new Score(7);
        NormalDefaultFrame normalFrame = new NormalDefaultFrame(firstScore, secondScore);
        assertTrue(normalFrame.isSpare(firstScore, secondScore));
    }

    @Test
    void convertSpare() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new Score(3));
        normalDefaultFrame.makeScore(new Score(7), 2);
        assertThat(normalDefaultFrame.convert()).isEqualTo("3|/");
    }

    @Test
    void convertStrike() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new Score(10));
        assertThat(normalDefaultFrame.convert()).isEqualTo("X");
    }

    @Test
    void convertFirstPitch() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new Score(3));
        assertThat(normalDefaultFrame.convert()).isEqualTo("3|");
    }

    @Test
    void convert() {
        DefaultFrame normalDefaultFrame = new NormalDefaultFrame(new Score(4));
        normalDefaultFrame.makeScore(new Score(5), 2);
        assertThat(normalDefaultFrame.convert()).isEqualTo("4|5");
    }

}
