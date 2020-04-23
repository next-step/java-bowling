package bowling.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Board;
import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.FrameResult;
import bowling.domain.Game;
import bowling.domain.NormalFrame;

class ResultViewTest {
    private Game game;
    private NormalFrame normalFrame;
    private Frame frame;
    private Board board;

    @BeforeEach
    void setUp() {
        game = new Game("PJS");
        normalFrame = (NormalFrame) game.startGame();
        frame = normalFrame.bowl(10);

    }

    @DisplayName("스트라이크가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard() {
        board = normalFrame.createBoard();
        game = new Game("PJS", board);
        String playFrameScoreBoard = ResultView.print(game);
        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |      |      |      |      |      |      |      |      |      |\n" +
                "|      |      |      |      |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("스페어가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard2() {
        frame.bowl(8).bowl(2);
        board = normalFrame.createBoard();
        game = new Game("PJS", board);
        String playFrameScoreBoard = ResultView.print(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |\n" +
                "|      |  20  |      |      |      |      |      |      |      |      |      |\n");

    }

    @DisplayName("거터가 있을때 점수판을 확인한다")
    @Test
    void playFrameScoreBoard3() {
        frame = frame.bowl(8).bowl(2);
        frame = frame.bowl(7).bowl(0);
        board = normalFrame.createBoard();
        game = new Game("PJS", board);
        String playFrameScoreBoard = ResultView.print(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |\n" +
                "|      |  20  |  37  |  44  |      |      |      |      |      |      |      |\n");
    }

    @DisplayName("10프레임까지 확인해본다 퍼펙트 게임일 경우")
    @Test
    void playFrameScoreBoard4() {
        for (int i = 0; i < 7; i++) {
            frame = frame.bowl(10);
        }

        NormalFrame frame9 = (NormalFrame) frame;
        frame9.bonus();
        frame = frame.bowl(10);

        frame.bowl(10).bowl(10).bowl(10);

        board = normalFrame.createBoard();
        game = new Game("PJS", board);
        String playFrameScoreBoard = ResultView.print(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|X |\n" +
                "|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  300   |\n");
    }

    @DisplayName("마지막 프레임에 보너스 투구가 있을때 확인한다")
    @Test
    void playFinalFrameScoreBoard() {
        for (int i = 0; i < 7; i++) {
            frame = frame.bowl(10);
        }

        NormalFrame frame9 = (NormalFrame) frame;
        frame9.bonus();
        frame = frame.bowl(10);
        frame.bowl(10).bowl(2).bowl(3);

        board = normalFrame.createBoard();
        game = new Game("PJS", board);
        String playFrameScoreBoard = ResultView.print(game);

        assertThat(playFrameScoreBoard).isEqualTo(
                "|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|2|3 |\n" +
                "|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  262 |  277   |\n");
    }
}
