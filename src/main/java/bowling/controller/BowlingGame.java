package bowling.controller;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Arrays;

public class BowlingGame {
    public static void start() {
        GameResult gameResult1
                = GameResult.of(PlayerName.of(InputView.getFirstPlayerName()), Frame.createTenFrames());
        GameResult gameResult2
                = GameResult.of(PlayerName.of(InputView.getSecondPlayerName()), Frame.createTenFrames());

        ResultView.print(GameResults.of(Arrays.asList(gameResult1, gameResult2)));
    }
}