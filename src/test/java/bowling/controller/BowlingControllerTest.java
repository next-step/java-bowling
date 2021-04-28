package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingControllerTest {
    BowlingController systemUnderTest;
    List<String> record;

    @BeforeEach
    void setUp() {
        record = new ArrayList<>();
        systemUnderTest = new BowlingController(new TestingInputView(), new TestingResultView());
    }

    @Test
    @DisplayName("프레임 별 핀 수를 입력받고 해당 결과 표시, 점수 출력을 모든 프레임 종료시 까지 반복한다.")
    public void playGame() throws Exception {
        List<Player> players = Collections.singletonList(new Player("KSB"));
        systemUnderTest.playGame(new Players(players));
        assertThat(String.join(" -> ", record)).isEqualTo("printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult -> " +
                "pinCount -> printResult");
    }

    class TestingInputView extends InputView {

        @Override
        public String pinCount(Name name) {
            record.add("pinCount");
            return "10";
        }
    }

    class TestingResultView extends ResultView {
        @Override
        public void printResult(List<Name> names, List<Marks> marks, List<Scores> scores) {
            record.add("printResult");
        }
    }
}