package bowling;

import bowling.controller.BowlingController;
import bowling.entity.frame.Frame;
import bowling.entity.frame.FrameResult;
import bowling.entity.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
            assertThat(frameScore(bowlingPrintFrame, i).equals("X")).isTrue();
        }

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).equals("X")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).equals("X|X")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).equals("X|X|X")).isTrue();

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("볼링 게임 정상 진행")
    public void bowling() {
        BowlingController bowlingController = new BowlingController();
        Frame bowlingFrame = new NormalFrame(START_FRAME);
        Frame bowlingPrintFrame = bowlingFrame;

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 0).equals("X")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 5);
        assertThat(frameScore(bowlingPrintFrame, 1).equals("5")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 1).equals("5|0")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 2).equals("4")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 2).equals("4|4")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 5);
        assertThat(frameScore(bowlingPrintFrame, 3).equals("5")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 3).equals("5|0")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 1);
        assertThat(frameScore(bowlingPrintFrame, 4).equals("1")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 2);
        assertThat(frameScore(bowlingPrintFrame, 4).equals("1|2")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 5).equals("4")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 6);
        assertThat(frameScore(bowlingPrintFrame, 5).equals("4|/")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 7);
        assertThat(frameScore(bowlingPrintFrame, 6).equals("7")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 6).equals("7|0")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 9);
        assertThat(frameScore(bowlingPrintFrame, 7).equals("9")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 0);
        assertThat(frameScore(bowlingPrintFrame, 7).equals("9|0")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 8).equals("X")).isTrue();

        bowlingFrame = bowlingController.bowl(bowlingFrame, 4);
        assertThat(frameScore(bowlingPrintFrame, 9).equals("4")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 6);
        assertThat(frameScore(bowlingPrintFrame, 9).equals("4|/")).isTrue();
        bowlingFrame = bowlingController.bowl(bowlingFrame, 10);
        assertThat(frameScore(bowlingPrintFrame, 9).equals("4|/|X")).isTrue();

        assertThat(bowlingFrame.bowlingGameEnd()).isTrue();
    }

    private String frameScore(Frame bowlingPrintFrame, int i) {
        return bowlingPrintFrame.bowlingBoard().boardResult().get(i).bowlingScore();
    }
}
