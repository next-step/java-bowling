package bowling;

import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class NormalFrameTest {
    @Test
    void isFirstPitch() {
        NormalFrame normalFrame = new NormalFrame(new Score(10));
        assertThat(normalFrame.isFirstPitch()).isEqualTo(true);
    }

    @Test
    void isSpare() {
        Score firstScore = new Score(3);
        Frame normalFrame = new NormalFrame(firstScore);
        Score secondScore = new Score(7);
        normalFrame.setSecondScore(secondScore);
        assertThat(normalFrame.isSpare(firstScore,secondScore)).isEqualTo(true);
    }
    @Test
    void convertSpare() {
        Frame normalFrame = new NormalFrame(new Score(3));
        normalFrame.setSecondScore(new Score(7));
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
        normalFrame.setSecondScore(new Score(5));
        assertThat(normalFrame.convert()).isEqualTo("4|5");
    }

}
