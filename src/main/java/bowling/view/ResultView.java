package bowling.view;

import bowling.dto.BowlingResult;
import bowling.exception.NotSupportInstanceException;

public class ResultView {

    private ResultView() {
        throw new NotSupportInstanceException();
    }

    public static void bowlingResultView(BowlingResult bowlingResult) {
        System.out.println(ResultBuilder.bowlingResultChart(bowlingResult));
    }
}
