package bowling;

import bowling.controller.BowlingController;
import bowling.entity.frame.Frame;
import bowling.entity.frame.FrameResult;
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
        Frame bowlingPrintFrame = bowlingFrame;

        for (int i = 0; i < 9; i++) {
            bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
            assertThat(frameScore(bowlingPrintFrame, i).bowlingScore().equals("X")).isTrue();
        }
        assertThat(frameScore(bowlingPrintFrame, 6).totalScore() == 210 ).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).bowlingScore().equals("X")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 7).totalScore() == 240 ).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).bowlingScore().equals("X|X")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 8).totalScore() == 270 ).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).bowlingScore().equals("X|X|X")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 9).totalScore() == 300 ).isTrue();
        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("볼링 게임 정상 진행")
    public void bowling() {
        BowlingController bowlingController = new BowlingController();
        Frame bowlingFrame = new NormalFrame(START_FRAME);
        Frame bowlingPrintFrame = bowlingFrame;

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 0).bowlingScore().equals("X")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 0).totalScore() == -1 ).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 5);
        assertThat(frameScore(bowlingPrintFrame, 1).bowlingScore().equals("5")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 1).totalScore() == -1 ).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);

        assertThat(frameScore(bowlingPrintFrame, 1).bowlingScore().equals("5|0")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 0).totalScore() == 15).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 1).totalScore() == 20).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 2).bowlingScore().equals("4")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 2).bowlingScore().equals("4|4")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 2).totalScore() == 28).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 5);
        assertThat(frameScore(bowlingPrintFrame, 3).bowlingScore().equals("5")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 3).bowlingScore().equals("5|0")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 3).totalScore() == 33).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 1);
        assertThat(frameScore(bowlingPrintFrame, 4).bowlingScore().equals("1")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 2);
        assertThat(frameScore(bowlingPrintFrame, 4).bowlingScore().equals("1|2")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 4).totalScore() == 36).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 5).bowlingScore().equals("4")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 6);
        assertThat(frameScore(bowlingPrintFrame, 5).bowlingScore().equals("4|/")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 5).totalScore() == -1).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 7);
        assertThat(frameScore(bowlingPrintFrame, 6).bowlingScore().equals("7")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 6).bowlingScore().equals("7|0")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 6).totalScore() == 60).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 9);
        assertThat(frameScore(bowlingPrintFrame, 7).bowlingScore().equals("9")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 7).bowlingScore().equals("9|0")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 7).totalScore() == 69).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 8).bowlingScore().equals("X")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 8).totalScore() == -1).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 9).bowlingScore().equals("4")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 6);
        assertThat(frameScore(bowlingPrintFrame, 9).bowlingScore().equals("4|/")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).bowlingScore().equals("4|/|X")).isTrue();
        assertThat(frameScore(bowlingPrintFrame, 9).totalScore() == 109).isTrue();

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }

    private FrameResult frameScore(Frame bowlingPrintFrame, int i) {
        return bowlingPrintFrame.bowlingBoard().boardResult().get(i);
    }
}
