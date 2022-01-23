package bowling;

import bowling.domain.Score;
import bowling.domain.frame.LastDefaultFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastDefaultFrameTest {
    @Test
    void convertFirstPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new Score(5));
        assertThat(lastFrame.convert()).isEqualTo("5|");
    }

    @Test
    void convertStrikeFirstPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new Score(10));
        assertThat(lastFrame.convert()).isEqualTo("X|");
    }

    @Test
    void convertStrikeSecondPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new Score(10), new Score(10));
        assertThat(lastFrame.convert()).isEqualTo("X|X");
    }

    @Test
    void convertSpareSecondPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new Score(3), new Score(7));
        assertThat(lastFrame.convert()).isEqualTo("3|/|");
    }

    @Test
    void convertSecondPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new Score(3), new Score(4));
        assertThat(lastFrame.convert()).isEqualTo("3|4");
    }

    @Test
    void convertStrikeThirdPitch() {
        LastDefaultFrame lastFrame = new LastDefaultFrame(new Score(10), new Score(10), new Score(10));
        assertThat(lastFrame.convert()).isEqualTo("X|X|X");
    }
}
