package bowling.view;

import bowling.domain.BowlingGame;
import bowling.exception.NotSupportInstanceException;

public class ResultView {

    private ResultView() {
        throw new NotSupportInstanceException();
    }

    public static void bowlingResultView(BowlingGame bowlingGame) {
        System.out.println(ResultBuilder.bowlingResultChart(bowlingGame));
    }
}
