package bowling;

import bowling.domain.Score;
import bowling.domain.frame.LastFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class LastFrameTest {
    @Test
    void isLastFrameTest() {
        LastFrame lastFrame = new LastFrame(new Score(10));
        assertThat(lastFrame.isLastFrame()).isEqualTo(true);
    }

    @Test
    void isFirstPitch() {
        LastFrame lastFrameA = new LastFrame(new Score(10));
        LastFrame lastFrameB = new LastFrame(new Score(7));

        lastFrameB.setSecondScore(new Score(3));

        assertThat(lastFrameA.isFirstPitch()).isEqualTo(true);
        assertThat(lastFrameB.isFirstPitch()).isEqualTo(false);

    }

    @Test
    void isSecondPitch() {
        LastFrame lastFrameA = new LastFrame(new Score(10));
        LastFrame lastFrameB = new LastFrame(new Score(7));
        LastFrame lastFrameC = new LastFrame(new Score(10));

        lastFrameB.setSecondScore(new Score(3));
        lastFrameC.setSecondScore(new Score(10));
        lastFrameC.setThirdScore(new Score(10));

        assertThat(lastFrameA.isSecondPitch()).isEqualTo(false);
        assertThat(lastFrameB.isSecondPitch()).isEqualTo(true);
        assertThat(lastFrameC.isSecondPitch()).isEqualTo(false);
    }

    @Test
    void convert() {
        LastFrame lastFrameA = new LastFrame(new Score(10));
        LastFrame lastFrameB = new LastFrame(new Score(7));
        LastFrame lastFrameC = new LastFrame(new Score(10));
        LastFrame lastFrameD = new LastFrame(new Score(10));

        lastFrameB.setSecondScore(new Score(3));
        lastFrameC.setSecondScore(new Score(10));
        lastFrameD.setSecondScore(new Score(3));
        lastFrameC.setThirdScore(new Score(10));
        lastFrameD.setThirdScore(new Score(7));

        assertThat(lastFrameA.convert()).isEqualTo("X|");
        assertThat(lastFrameB.convert()).isEqualTo("7|/|");
        assertThat(lastFrameC.convert()).isEqualTo("X|X|X");
        assertThat(lastFrameD.convert()).isEqualTo("X|3|/");

    }
}
