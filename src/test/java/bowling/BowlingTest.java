package bowling;

import bowling.controller.BowlingController;
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
        BowlingController bowlingController = new BowlingController();
        Frame bowlingFrame = new NormalFrame(START_FRAME);

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("볼링 게임 정상 진행")
    public void bowling() {
        BowlingController bowlingController = new BowlingController();
        Frame bowlingFrame = new NormalFrame(START_FRAME);

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 5);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 5);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 1);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 2);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 6);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 7);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 9);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 6);
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }
}
