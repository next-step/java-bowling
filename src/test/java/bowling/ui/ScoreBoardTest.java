package bowling.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {
    @Test
    @DisplayName("볼링 점수판 출력 테스트")
    void printScoreBoardTest() {
        ScoreBoard scoreBoard = new ScoreBoard("PDJ");

        scoreBoard.addData(1, "X");
        scoreBoard.addData(2, "8");
        scoreBoard.addData(2, "/");
        scoreBoard.addData(3, "2");
        scoreBoard.addData(3, "4");
        scoreBoard.addData(4, "X");
        scoreBoard.addData(5, "3");
        scoreBoard.addData(5, "1");
        scoreBoard.addData(6, "7");
        scoreBoard.addData(6, "/");
        scoreBoard.addData(7, "7");
        scoreBoard.addData(7, "/");
        scoreBoard.addData(8, "-");
        scoreBoard.addData(8, "-");
        scoreBoard.addData(9, "3");
        scoreBoard.addData(9, "2");
        scoreBoard.addData(10, "3");
        scoreBoard.addData(10, "/");
        scoreBoard.addData(10, "1");

        scoreBoard.printScoreBoard();
    }
}
