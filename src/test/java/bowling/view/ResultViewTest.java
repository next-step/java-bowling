package bowling.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Game;
import bowling.domain.NormalFrame;

class ResultViewTest {
    private static ResultView resultView = ResultView.getResultView();
    private Game game;
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        game = new Game("PJS");
        normalFrame = game.startGame();
        normalFrame.bowl(10);
    }

    @DisplayName("입력받은 유저이름으로 점수판을 생성한다.")
    @Test
    void initScoreBoard() {
        String initScoreBoard = resultView.initScoreBoard("PJS");
        assertThat(initScoreBoard).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                + "|  PJS |      |      |      |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("프레임의 투구를 보여준다.")
    @Test
    void playFrameResult() {
        String printPlayFrame = resultView.playFrameResult(normalFrame);
        assertThat(printPlayFrame).isEqualTo("1프레임 투구 : 10\n");
    }

    @DisplayName("스트라이크가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard() {
        String playFrameScoreBoard = resultView.playFrameScoreBoard(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                + "|  PJS |  X   |      |      |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("스페어가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard2() {
        NormalFrame nextFrame = normalFrame.createNextFrame(true);
        nextFrame.bowl(8);
        nextFrame.bowl(2);
        game.addFrame(nextFrame);
        String playFrameScoreBoard = resultView.playFrameScoreBoard(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                + "|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("거터가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard3() {
        NormalFrame nextFrame = normalFrame.createNextFrame(true);
        nextFrame.bowl(8);
        nextFrame.bowl(2);
        game.addFrame(nextFrame);

        NormalFrame nextFrame2 = normalFrame.createNextFrame(true);
        nextFrame2.bowl(7);
        nextFrame2.bowl(0);
        game.addFrame(nextFrame2);

        String playFrameScoreBoard = resultView.playFrameScoreBoard(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                + "|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n");
    }
}
