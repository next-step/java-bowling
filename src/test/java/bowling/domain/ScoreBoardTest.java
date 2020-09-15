package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    @DisplayName("점수판 출력 테스트")
    @Test
    void print() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.from("HSK");

        // when
        System.out.println(scoreBoard.printableStatus());
    }
}
