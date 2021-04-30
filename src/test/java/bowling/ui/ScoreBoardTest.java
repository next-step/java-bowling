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
        scoreBoard.addData(3, "8|/");
        scoreBoard.addData(4, "7|-");
        scoreBoard.addData(10, "2|-|8");

        scoreBoard.printScoreBoard();
    }
}
