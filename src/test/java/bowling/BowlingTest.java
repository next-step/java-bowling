package bowling;

import bowling.entity.Pin;
import bowling.entity.frame.Frame;
import bowling.entity.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.controller.BowlingController.START_FRAME;
import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    @Test
    @DisplayName("전체 Strike 볼링 게임 정상 진행")
    public void allStrikeBowling() {
        Frame bowlingFrame = new NormalFrame(START_FRAME);

        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("볼링 게임 정상 진행")
    public void bowling() {
        Frame bowlingFrame = new NormalFrame(START_FRAME);

        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(5));
        bowlingFrame = bowlingFrame.pinResult(new Pin(0));
        bowlingFrame = bowlingFrame.pinResult(new Pin(4));
        bowlingFrame = bowlingFrame.pinResult(new Pin(4));
        bowlingFrame = bowlingFrame.pinResult(new Pin(5));
        bowlingFrame = bowlingFrame.pinResult(new Pin(0));
        bowlingFrame = bowlingFrame.pinResult(new Pin(1));
        bowlingFrame = bowlingFrame.pinResult(new Pin(2));
        bowlingFrame = bowlingFrame.pinResult(new Pin(4));
        bowlingFrame = bowlingFrame.pinResult(new Pin(6));
        bowlingFrame = bowlingFrame.pinResult(new Pin(7));
        bowlingFrame = bowlingFrame.pinResult(new Pin(0));
        bowlingFrame = bowlingFrame.pinResult(new Pin(9));
        bowlingFrame = bowlingFrame.pinResult(new Pin(0));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));
        bowlingFrame = bowlingFrame.pinResult(new Pin(4));
        bowlingFrame = bowlingFrame.pinResult(new Pin(6));
        bowlingFrame = bowlingFrame.pinResult(new Pin(10));

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }
}
