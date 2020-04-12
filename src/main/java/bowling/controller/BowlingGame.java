package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.PlayerName;
import bowling.domain.RandomGenerator;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public static void start() {
        RandomGenerator randomGenerator = new RandomGenerator();
        PlayerName playerName = PlayerName.of(InputView.getPlayerName());
        Frames tenFrames = Frame.createTenFrames(randomGenerator);

        ResultView.print(playerName, tenFrames);
    }
}