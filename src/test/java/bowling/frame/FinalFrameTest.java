package bowling.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pitch.Normal;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    @Test
    @DisplayName("final pitch 에서 strike spare 플레이 경우 20 count 반환")
    void playSpare() {
        Frame finalFrame = new FinalFrame(Arrays.asList(new Normal(10)));
        finalFrame = finalFrame.play(new KnockedPins(7));
        finalFrame = finalFrame.play(new KnockedPins(3));
        Game game = new Game(Arrays.asList(finalFrame));
        assertThat(finalFrame.calculateScore(game).getScore()).isEqualTo(20);
    }
}
