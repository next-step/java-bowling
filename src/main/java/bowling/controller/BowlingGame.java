package bowling.controller;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public static void start() {
        GameResult gameResult
                = GameResult.of(PlayerName.of(InputView.getPlayerName()), Frame.createTenFrames());

        ResultView.print(gameResult);
    }
}