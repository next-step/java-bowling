package bowling;

import bowling.domain.ScoreBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    @Test
    @DisplayName("템플릿 전역 변수 생성 테스트")
    void templateStaticVariable() {
        ScoreBoard.register("KSJ");
//        ResultView.printFrames(new ArrayList<>());
    }
}
