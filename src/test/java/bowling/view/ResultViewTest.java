package bowling.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultViewTest {

    @DisplayName("결과 현황판 출력 확인")
    @Test
    void testCase1() {
        ResultView resultView = new ResultView();
        System.out.println(resultView.printResult());
    }
}
