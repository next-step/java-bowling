package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.dto.BowlingResult;
import bowling.exception.NotSupportInstanceException;

public class ResultView {

    private ResultView() {
        throw new NotSupportInstanceException();
    }

    public static void bowlingResultView(BowlingGames bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames.values()) {
            System.out.println(ResultBuilder.bowlingResultChart(BowlingResult.from(bowlingGame)));
        }
    }
}
