package bowling.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultViewTest {
    private static ResultView resultView = ResultView.getResultView();

    @DisplayName("입력받은 유저이름으로 점수판을 생성한다.")
    @Test
    void initScoreBoard() {
        String initScoreBoard = resultView.initScoreBoard("PJS");
        assertThat(initScoreBoard).isEqualTo(
                "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n"
                + "|  PJS |      |      |      |      |      |      |      |      |      |      |\n");
    }
}
