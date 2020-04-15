package bowling.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.FinalFrame;
import bowling.domain.Game;
import bowling.domain.NormalFrame;

class ResultViewTest {
    private static ResultView resultView = ResultView.getResultView();
    private Game game;
    private NormalFrame normalFrame;
    private int bowl = 0;

    @BeforeEach
    void setUp() {
        game = new Game("PJS");
        normalFrame = game.startGame();
        bowl = normalFrame.bowl(10);
        game.addFrame(normalFrame);
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
        String printPlayFrame = resultView.playFrameResult(normalFrame.getFrameNum(), bowl);
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
        NormalFrame nextFrame = normalFrame.createNextFrame();
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
        NormalFrame nextFrame = normalFrame.createNextFrame();
        nextFrame.bowl(8);
        nextFrame.bowl(2);
        game.addFrame(nextFrame);

        NormalFrame nextFrame2 = normalFrame.createNextFrame();
        nextFrame2.bowl(7);
        nextFrame2.bowl(0);
        game.addFrame(nextFrame2);

        String playFrameScoreBoard = resultView.playFrameScoreBoard(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                + "|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("마지막 프레임에 보너스 투구가 있을때 확인한다")
    @Test
    void playFinalFrameScoreBoard() {
        for (int i = 0; i < 8; i++) {
            game.addFrame(normalFrame);
        }
        FinalFrame finalFrame = normalFrame.createFinalFrame(true);

        finalFrame.bowl(10);
        finalFrame.bowl(2);
        finalFrame.bowl(3);
        game.addFrame(finalFrame);
        assertThat(resultView.playFrameScoreBoard(game)).isEqualTo("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                                                                   + "|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   | X|2|3|\n");
    }
}
