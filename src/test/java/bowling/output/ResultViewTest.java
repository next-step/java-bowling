package bowling.output;

import org.junit.jupiter.api.Test;

class ResultViewTest {

    @Test
    void name() {
        ResultView resultView = new ResultView("123");
        resultView.print(frames);

    }
}