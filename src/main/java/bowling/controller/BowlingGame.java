package bowling.controller;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;
import bowling.view.InputView;
import bowling.view.ResultView2;

import java.util.Arrays;

public class BowlingGame {
    public static void start() {
        GameResult gameResult1
                = GameResult.of(PlayerName.of(InputView.getPlayerName()), Frame.createTenFrames());
        GameResult gameResult2
                = GameResult.of(PlayerName.of(InputView.getPlayerName()), Frame.createTenFrames());

        ResultView2.print(GameResults.of(Arrays.asList(gameResult1, gameResult2)));
    }
}