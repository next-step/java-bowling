package bowling.ui;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {
    @Test
    @DisplayName("볼링 점수판 출력 테스트")
    void printScoreBoardTest() {
        ScoreBoard scoreBoard = new ScoreBoard("PDJ");

        scoreBoard.addData(1, Arrays.asList("X"));
        scoreBoard.addData(2, Arrays.asList("8", "/"));
        scoreBoard.addData(3, Arrays.asList("7", "-"));
        scoreBoard.addData(10, Arrays.asList("3", "/", "X"));

        scoreBoard.printScoreBoard();
    }
}
