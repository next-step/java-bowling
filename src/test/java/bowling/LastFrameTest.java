package bowling;

import bowling.domain.Score;
import bowling.domain.frame.LastFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {
    @Test
    void convertFirstPitch() {
        LastFrame lastFrame = new LastFrame(new Score(5));
        assertThat(lastFrame.convert()).isEqualTo("5|");
    }

    @Test
    void convertStrikeFirstPitch() {
        LastFrame lastFrame = new LastFrame(new Score(10));
        assertThat(lastFrame.convert()).isEqualTo("X|");
    }

    @Test
    void convertStrikeSecondPitch() {
        LastFrame lastFrame = new LastFrame(new Score(10), new Score(10));
        assertThat(lastFrame.convert()).isEqualTo("X|X");
    }

    @Test
    void convertSpareSecondPitch() {
        LastFrame lastFrame = new LastFrame(new Score(3), new Score(7));
        assertThat(lastFrame.convert()).isEqualTo("3|/|");
    }

    @Test
    void convertSecondPitch() {
        LastFrame lastFrame = new LastFrame(new Score(3), new Score(4));
        assertThat(lastFrame.convert()).isEqualTo("3|4");
    }

    @Test
    void convertStrikeThirdPitch() {
        LastFrame lastFrame = new LastFrame(new Score(10), new Score(10), new Score(10));
        assertThat(lastFrame.convert()).isEqualTo("X|X|X");
    }
}
