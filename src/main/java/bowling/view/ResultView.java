package bowling.view;

import bowling.dto.BowlingResult;
import bowling.dto.BowlingResults;
import bowling.exception.NotSupportInstanceException;

public class ResultView {

    private ResultView() {
        throw new NotSupportInstanceException();
    }

    public static void bowlingResultView(BowlingResults bowlingResults) {
        for (BowlingResult bowlingResult : bowlingResults.getBowlingResults()) {
            System.out.println(ResultBuilder.bowlingResultChart(bowlingResult));
        }
    }
}
