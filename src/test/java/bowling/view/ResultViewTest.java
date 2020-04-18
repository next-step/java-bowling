package bowling.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.NormalFrame;

class ResultViewTest {
    private static ResultView resultView = ResultView.getResultView();
    private Game game;
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        game = new Game("PJS");
        Frame frame = game.startGame();
        normalFrame = (NormalFrame) frame.bowl(10);
        game.addFrame(normalFrame);
    }

    @DisplayName("스트라이크가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard() {
        String playFrameScoreBoard = resultView.print(game);
        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |      |      |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("스페어가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard2() {
        Frame nextFrame = normalFrame.create();
        nextFrame.bowl(8);
        nextFrame.bowl(2);
        game.addFrame(nextFrame);
        String playFrameScoreBoard = resultView.print(game);
        System.out.println(playFrameScoreBoard);

        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("거터가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard3() {
        Frame nextFrame = normalFrame.create();
        nextFrame.bowl(8);
        nextFrame.bowl(2);
        game.addFrame(nextFrame);

        Frame nextFrame2 = normalFrame.create();
        nextFrame2.bowl(7);
        nextFrame2.bowl(0);
        game.addFrame(nextFrame2);

        String playFrameScoreBoard = resultView.print(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("마지막 프레임에 보너스 투구가 있을때 확인한다")
    @Test
    void playFinalFrameScoreBoard() {

        for (int i = 0; i < 8; i++) {
            normalFrame = (NormalFrame) normalFrame.create();
            normalFrame.bowl(10);
            game.addFrame(normalFrame);

        }

        normalFrame.bonus();
        Frame finalFrame = normalFrame.create();

        finalFrame.bowl(10);
        finalFrame.bowl(2);
        finalFrame.bowl(3);
        game.addFrame(finalFrame);
        assertThat(resultView.print(game)).isEqualTo(
                "|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|2|3 |\n");
    }
}
