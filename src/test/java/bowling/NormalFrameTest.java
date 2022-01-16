package bowling;

import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalFrameTest {


    @Test
    void isSpare() {
        Score firstScore = new Score(3);
        Score secondScore = new Score(7);
        NormalFrame normalFrame = new NormalFrame(firstScore, secondScore);
        assertTrue(normalFrame.isSpare(firstScore, secondScore));
    }

    @Test
    void convertSpare() {
        Frame normalFrame = new NormalFrame(new Score(3));
        normalFrame.makeScore(new Score(7), 2);
        assertThat(normalFrame.convert()).isEqualTo("3|/");
    }

    @Test
    void convertStrike() {
        Frame normalFrame = new NormalFrame(new Score(10));
        assertThat(normalFrame.convert()).isEqualTo("X");
    }

    @Test
    void convertFirstPitch() {
        Frame normalFrame = new NormalFrame(new Score(3));
        assertThat(normalFrame.convert()).isEqualTo("3|");
    }

    @Test
    void convert() {
        Frame normalFrame = new NormalFrame(new Score(4));
        normalFrame.makeScore(new Score(5), 2);
        assertThat(normalFrame.convert()).isEqualTo("4|5");
    }

}
